from K_means import K_means

Kmeans = K_means(3)
Kmeans.random_assign()
Kmeans.calc_new_centroids()

new_E = None

while new_E != Kmeans.E or new_E is None:
    if new_E is not None:
        Kmeans.E = new_E
    Kmeans.centroid_based_assign()
    Kmeans.calc_new_centroids()
    new_E = Kmeans.calc_E()

for group in Kmeans.groups:
    for member in group.members:
        print(member.attributtes)
    print()