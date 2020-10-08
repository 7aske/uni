# Installing an operating system

## Introduction

In the following article we will go thorugh the process of installing a very minimal operating system, namely Arch Linux. Arch Linux is a variant (or distribution as it is known in the community) of Linux operating system and is known to be one of the most difficult to install since it involves only the command line and little to no intructions by default.

Firstly what you need to do is get yourself and up-to-date version of the Arch Linux installation ISO file from Arch Linux official [site](https://archlinux.org). After downloading said ISO file should be 'burded' to a USB stick. Various tools are available from Windows machines such as Rufus. After finshing the 'burning' process leave the USB stick in the PC and reboot your PC. You will be prompted to boot off of and external USB drive. Press enter and continue to the next section.

After the booting process finishes you are left in the Arch Linux Live terminal. There you have all the needed tools available to install the operating system. Following lines will list the commands and their descriptions.

Before going on with the acutall installation we have to prepare a few things.

`lsblk`

This will list your hard drives. Identify yours which should be in the format `/dev/sdX`. aRun the following command to enter the interface in which you will format your drive accordingly. 

`fdisk /dev/sda`

Fdisk is a tool for formating drives. You need to crate three partitions in order to continiue with the intallation process. Partitions are logical sections of the hard disk. Each partition has its own purpose and function.

`n -> 1 -> <Enter> -> +512M -> <Enter>`

Following key sequence will create a `boot` partition which will contain the Arch Linux Kernel. Kernel is a piece of software that is the crucial link between the actual hardware and other user-space software that you are running on the machine. 

`n -> 2 -> <Enter> -> <Enter>`

This will allocate the rest of the space on the hard drive for your root partition which will contain all of your files and programs. Press `w` and `<Enter>` to write changes to the disk.

`mkfs.ext4 /dev/sda1`
`mkfs.ext4 /dev/sda2`

These commands will format the partitions according to your `fdisk` settings.

Next we need to mount said partitions to the mount points we will create.

`mount /dev/sda1 /mnt`
`mkdir /mnt/boot`

Ok this is getting a bit lengthy and we're not even getting started. I don't even know if on the spot with the topic so im just going to leave it as it is. Cheers.
