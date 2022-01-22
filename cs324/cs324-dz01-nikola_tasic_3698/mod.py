from math import log

num = int(input("Enter a number: "))

log2    = log(2)
log_num = log(num)

res = int(log_num / log2) == (log_num / log2)

print("{} is{} a power of 2".format(num, "" if res else " NOT"))
