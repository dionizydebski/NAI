class Group:
    id = 0
    def __init__(self):
        self.members = []
        self.centroid = []
        Group.id += 1
        self.id  = Group.id

    def calc_centroid(self):
        self.centroid.clear()
        if len(self.members) > 0:
            for i in range(len(self.members[0].attributtes)):
                sum = 0
                for member in self.members:
                    sum += float(member.attributtes[i])
                self.centroid.append(sum / len(self.members))
        else:
            print("No members")

