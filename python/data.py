def get_wines():
    with open('old.txt', 'r') as r:
        for l in r.readlines():
            if 'public' in l:
                print('-' * 80)
                print(l)
                splits = l.split('"')
                wine_id = splits[1]
                wine_name = splits[3]
                technical_name = splits[0].strip().split(' ')[4]
                print(technical_name)
                print(wine_id)
                print(wine_name)
                yield (technical_name, wine_id, wine_name)


def get_millesimes():
    return list(range(1989, 2017))


if __name__ == '__main__':
    for l in list(get_wines()):
        print(l)
