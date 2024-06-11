import random

from Group import Group
from Observation import Observation

class K_means:
    def __init__(self, k):
        self.k = k
        self.E = -1
        self.data = Observation.read_csv('test.csv')
        self.groups = []
        for i in range(k):
            self.groups.append(Group())

    def random_assign(self):
        for o in self.data:
            o.group = random.choice(self.groups)
            o.group.members.append(o)

    def centroid_based_assign(self):
        #czyszczenie grup
        for group in self.groups:
            group.members.clear()

        for o in self.data:
            new_group = None
            best_distance = None
            for group in self.groups:
                distance = o.distance(group.centroid)
                if best_distance is None or distance < best_distance:
                    best_distance = distance
                    new_group = group
            o.group = new_group
            o.group.members.append(o)

    def calc_E(self):
        new_E = 0

        for group in self.groups:
            tmp = 0
            for member in group.members:
                tmp += member.distance(group.centroid)
            print(group.id,'->',tmp)
            new_E += tmp
        print('New E: ',new_E,'\n')

        return new_E

    def calc_new_centroids(self):
        for group in self.groups:
            group.calc_centroid()