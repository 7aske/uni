# hello-kernel

## Uputstvo

```bash
# u drugom terminalu
sudo dmesg -w

make                       # kompilacija
sudo insmod ./hello-module # load modula
sudo rmmod hello-module    # unload modula
make clean                 # ciscenje radnog direktorijuma

# dmesg bi trebalo da odstampa da je uspesni ucitao hello-module
```

