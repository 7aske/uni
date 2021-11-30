import numpy as np
import matplotlib.pyplot as plt
import pandas as pd


def g(x):
    return np.exp((-1 / 2) * x) * np.cos((-2) * x)


def h(x):
    return np.exp((-3 / 2) * x) * np.sin(4 * x)


def main():
    x = np.linspace(0, 4, 501)
    g_func = g(x)
    h_func = h(x)
    dataframe = pd.DataFrame(list(zip(x, g_func, h_func)), columns=("x", "g", "h"))
    dataframe.to_csv("data.csv", index=False)



if __name__ == '__main__':
    main()
