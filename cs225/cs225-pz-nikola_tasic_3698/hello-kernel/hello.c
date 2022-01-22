#include <linux/module.h> /* Needed by all modules */
#include <linux/device.h> /* Needed by class_create */
#include <linux/kernel.h> /* Needed for KERN_INFO */
#include <linux/init.h>   /* Needed for the macros */
#include <linux/fs.h>     /* Needed for file operations */
#include <asm/uaccess.h>  /* Needed for put_user */


#define HELLO_SUCCESS 0
#define HELLO_FAILURE 1
#define HELLO_DEVICE_NAME "hello" /* Dev name as it appears in /proc/devices   */
#define BUFSIZE 128


// @formatter:off
static int     hello_init(void);
static void    hello_exit(void);
static ssize_t hello_read(struct file*, char*, size_t, loff_t*);
static ssize_t hello_write(struct file*, const char*, size_t, loff_t*);
static int     hello_open(struct inode *, struct file *);
static int     hello_release(struct inode *, struct file *);

static int    g_major;		    /* Major number assigned to our device driver */
static int    g_is_open = 0;	/* Is device open? Used to prevent multiple access to device */
static char   g_msg[BUFSIZE];	/* The msg the device will give when asked */
static char*  g_msg_ptr;
dev_t         dev_no;
struct class* m_class;          /* Device class e.g. /sys/class/hello */
// @formatter:on

// Structure containing function pointers to all the implemented operations
// that the module can preform on a character device.
struct file_operations fops = {
	.read = hello_read,
	.write = hello_write,
	.open = hello_open,
	.release = hello_release,
};

// Called when a process tries to open the device file
int hello_open(struct inode* inode, struct file* file) {
	static int counter = 0;

	// Prevent multiple accesses at the same time
	if (g_is_open)
		return -EBUSY;

	g_is_open++;
	sprintf(g_msg, "hello opened %d times\n", counter++);
	g_msg_ptr = g_msg;
	try_module_get(THIS_MODULE);

	return HELLO_SUCCESS;
}

// Called when process closes the device file
int hello_release(struct inode* inode, struct file* file) {
	// Allow new open calls
	g_is_open--;
	module_put(THIS_MODULE);

	return HELLO_SUCCESS;
}

ssize_t hello_read(struct file* file, char* buffer, size_t length, loff_t* off) {
	int bytes_read = 0;

	// this signifies the EOF
	if (*g_msg_ptr == 0)
		return 0;

	// copy data from module
	while (length && *g_msg_ptr) {

		// We copy the data to userspace.
		// Manually writing to memory wouldn't work
		put_user(*(g_msg_ptr++), buffer++);

		length--;
		bytes_read++;
	}

	// read function usually return the number of bytes read
	return bytes_read;
}

// Called when the process attempts to write to the device file
ssize_t hello_write(struct file* file, const char* user, size_t size, loff_t* off) {
	printk(KERN_ALERT "Write operation not supported\n");
	return -EPERM;
}


// Function that gets called when the module is loaded (insmod)
static int __init hello_init(void) {
	struct device* dev;
	printk(KERN_INFO "Loading module hello\n");

	// Register the character device
	g_major = register_chrdev(0, HELLO_DEVICE_NAME, &fops);
	if (g_major < 0) {
		// if the character device fails to register we gracefully exit
		printk(KERN_ALERT "Registering char device failed\n");
		return g_major;
	}
	printk(KERN_INFO "Crated a char device /dev/%s\n", HELLO_DEVICE_NAME);
	// printk(KERN_INFO "Char device can be created with the command:\n");
	// printk(KERN_INFO "mknod /dev/%s c %d 0\n", HELLO_DEVICE_NAME, g_major);

	dev_no = MKDEV(g_major, 0);

	// Create /sys/class/hello in preparation of creating /dev/hello
	m_class = class_create(THIS_MODULE, HELLO_DEVICE_NAME);
	if (IS_ERR(m_class)) {
		printk(KERN_ALERT "\nCan not create class");
		unregister_chrdev_region(dev_no, 1);
		return HELLO_FAILURE;
	}

	// Create /dev/hello for this char dev
	if (IS_ERR(dev = device_create(m_class, NULL, dev_no, NULL, HELLO_DEVICE_NAME))) {
		printk(KERN_WARNING "hello.ko can not create device /dev/hello\n");
		class_destroy(m_class);
		unregister_chrdev_region(dev_no, 1);
		return HELLO_FAILURE;
	}

	printk("%s", dev->init_name);

	return HELLO_SUCCESS;
}

// Function that gets called when the module is unloaded (rmmod)
static void __exit hello_exit(void) {
	// Clean up after ourselves
	// Remove the /dev/hello
	device_destroy(m_class, dev_no);
	// Remove class /sys/class/hello
	class_destroy(m_class);
	// Unregister the device
	unregister_chrdev(g_major, HELLO_DEVICE_NAME);

	printk(KERN_INFO "Unloading module hello\n");
}

// By default, kernel expect implementations of functions called init_module
// and cleanup_module, but you can declare functions with different identifiers
// and use them as the init and cleanup functions as long as you wrap them in
// the appropriate macros
module_init(hello_init)
module_exit(hello_exit)

// Additional kernel module information displayed with modinfo command
MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("Test kernel module");
MODULE_AUTHOR("Nikola Tasić");

