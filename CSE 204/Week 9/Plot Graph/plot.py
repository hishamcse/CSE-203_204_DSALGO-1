import matplotlib.pyplot as plt

ascend_merge = [0, 0, .1, .4, 3.9, 47.2]
ascend_quick = [0, .1, .8, 16.2, 1770.1, 178249.4]
descend_merge = [0, 0, .1, .4, 3.9, 46.9]
descend_quick = [0, .1, .8, 19, 1809.9, 184963.4]
random_merge = [0, 0, .1, .4, 4.5, 55.7]
random_quick = [0, .1, .7, 3, 21, 130]

n = [10, 100, 1000, 10000, 100000, 1000000]

fig = plt.figure(figsize=(10,10))
ax = fig.add_subplot(1, 1, 1)

# Move left y-axis and bottim x-axis to centre, passing through (0,0)
# ax.spines['left'].set_position('center')
# ax.spines['center'].set_position(('data', 0))
ax.set_xlim(10, 5000000)
ax.set_ylim(0.09, 500000)
ax.set_xscale('log')
ax.set_yscale('log')
# ax.spines['bottom'].set_position('center')

# Eliminate upper and right axes
# ax.spines['right'].set_color('none')
# ax.spines['top'].set_color('none')

plt.plot(n, ascend_merge, linestyle='solid', marker='o', lw=2, label="merge sort - ascending order")  # 'r' is the color red
plt.plot(n, ascend_quick, linestyle='solid', marker='o', lw=2, label="quick sort - ascending order")
plt.plot(n, descend_merge, linestyle='--', marker='o', lw=2,
         label="merge sort - descending order")  # 'r' is the color red
plt.plot(n, descend_quick, linestyle='--', marker='o', lw=2, label="quick sort - descending order")
plt.plot(n, random_merge, linestyle='solid', marker='o', lw=2, label="merge sort - random order")  # 'r' is the color red
plt.plot(n, random_quick, linestyle='--', marker='o', lw=2, label="quick sort - random order")

# plt.scatter(.603,.0144,color='black')
# plt.annotate('(0.603,0.0144)', xy=(.603,.0144))
plt.xlabel('n')
plt.ylabel('time (in milliseconds)')
plt.title('time vs n graph')
plt.legend()
plt.grid()
plt.margins(x=0, y=0)
plt.show()
