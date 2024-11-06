def tower_of_hanoi_approach1(source, destination, numOfDisk):
    temp = 6 - source - destination
    n = 0

    if numOfDisk == 1:
        print(f"Move Disk from {source} to {destination}")
        return 1
    else:
        n += tower_of_hanoi_approach1(source, temp, numOfDisk - 1)
        n += tower_of_hanoi_approach1(source, destination, 1)
        n += tower_of_hanoi_approach1(temp, destination, numOfDisk - 1)

    return n

def tower_of_hanoi_approach2(n, source, auxiliary, destination):
    if n == 1:
        print(f"Move disk 1 from {source} to {destination}")
        return 1

    moves = tower_of_hanoi_approach2(n - 1, source, destination, auxiliary)
    print(f"Move disk {n} from {source} to {destination}")
    moves += 1
    moves += tower_of_hanoi_approach2(n - 1, auxiliary, source, destination)
    return moves


if __name__ == "__main__":

    num_of_disks = 3
    total_moves = tower_of_hanoi_approach1(1, 3, num_of_disks)
    print(f"\nTotal number of moves are {total_moves} \n")

    n = 3
    total_moves = tower_of_hanoi_approach2(n, 'Source (1)', 'Auxiliary (2)', 'Destination (3)')
    print(f"\nTotal number of moves: {total_moves}")