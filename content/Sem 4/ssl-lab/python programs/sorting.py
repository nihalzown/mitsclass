def sort_list(lst):
    for i in range(len(lst)-1):
        for j in range(i+1, len(lst)):
            if lst[i] > lst[j]:
                lst[i], lst[j] = lst[j], lst[i]

num = input("Enter a list of numbers separated by commas: ")
lst = [int(n.strip()) for n in num.split(",")]
sort_list(lst)
print("Sorted list:", lst)

        
    