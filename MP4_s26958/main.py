import csv

def read_csv(file_name):
    data = []
    with open(file_name, 'r') as file:
        csv_reader = csv.reader(file)
        for row in csv_reader:
            data.append(row)
    return data

def get_train_arrey(train_file_array):
    zipped = list(zip(*train_file_array))

    train_array = []

    for i in range(len(zipped)):
        tmp = list(zip(zipped[i], zipped[len(zipped) - 1]))
        tmp_2 = set()
        for row in tmp:
            tmp_2.add(tuple([tuple(row), tmp.count(row)]))
        train_array.append(list(tmp_2))

    # zmienianie seta na liste z prawdopodobienstwem
    for i in range(len(train_array)):
        for j in range(len(train_array[i])):
            train_array[i][j] = list(train_array[i][j])
            train_array[i][j][0] = list(train_array[i][j][0])
            for k in range(len(train_array[len(train_array)-1])):
                if train_array[i][j][0][1] == train_array[len(train_array)-1][k][0][0] and i != len(train_array)-1:
                    train_array[i][j][1] /= train_array[len(train_array)-1][k][1]


    return train_array

def calculate_probability(train_array, arrey_to_check):
    best_probability = 0
    decision_arg = ""
    for i in range(len(train_array[len(train_array)-1])):
        probability = 1
        for j in range(len(arrey_to_check)):
            wygladzenie = True
            for k in range(len(train_array[j])):
                if arrey_to_check[j] == train_array[j][k][0][0] and train_array[len(train_array)-1][i][0][0] == train_array[j][k][0][1]:
                    probability *= train_array[j][k][1]
                    wygladzenie = False
            # wygladzanie
            if wygladzenie:
                probability *= (1 / (train_array[len(train_array)-1][i][1] + len(train_array[j]) // len(train_array[len(train_array)-1]) + 1))
        if probability > best_probability:
            best_probability = probability
            decision_arg = train_array[len(train_array)-1][i][0][0]
        probability = 1

    return decision_arg

def bayes(train_array, test_array):
    for row in test_array:
        print(row, calculate_probability(train_array, row))

    while input("Do you want to continue? (y/n) ") == "y":
        input_from_console = input().split(',')
        print(input_from_console, calculate_probability(train_array, input_from_console))

train_file_array = read_csv("trainingset.csv")
train_array = get_train_arrey(train_file_array)

test_file_array = read_csv("testset.csv")
bayes(train_array, test_file_array)