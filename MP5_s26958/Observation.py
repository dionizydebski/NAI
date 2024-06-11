import csv

class Observation:
    def __init__(self, attributtes):
        self.attributtes = attributtes
        self.group = None

    @staticmethod
    def read_csv(file_name):
        data = []
        with open(file_name, 'r') as file:
            csv_reader = csv.reader(file)
            for row in csv_reader:
                tmp = Observation(row)
                data.append(tmp)
        return data

    def distance(self, centroid):
        result = 0
        for i in range(len(self.attributtes)):
            result += (float(self.attributtes[i]) - centroid[i])**2
        return result
