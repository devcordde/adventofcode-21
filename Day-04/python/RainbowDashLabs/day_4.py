values: list[str] = [e.strip() for e in open("puzzle.txt") if e.strip()]
numbers = [int(num) for num in values[0].split(",")]
del values[0]


class Board:
    def __init__(self, field):
        self.field = [[int(val) for val in row.split(r" ") if val] for row in field]
        self.checked = [[False for val in self.field[0]] for val in self.field]
        self.last_num = 0

    def __repr__(self):
        return f"Done: {self.is_done()}\n{self.field.__repr__()}\n{self.checked.__repr__()}"

    def _find_index(self, num):
        for row, content in enumerate(self.field):
            if num in content:
                return row, content.index(num)
        return None

    def get_checked_row(self, row):
        return list(self.checked[row])

    def get_checked_col(self, col):
        return [row[col] for row in self.checked]

    def set(self, num):
        #if self.is_done():
        #    return True
        index = self._find_index(num)
        if index is None:
            return False
        self.last_num = num
        self.checked[index[0]][index[1]] = True
        return self._is_done_pos(index[0], index[1])

    def _is_done_pos(self, row, col):
        col = self.get_checked_col(col)
        row = self.get_checked_row(row)
        return sum(col) == len(col) or sum(row) == len(row)

    def is_done(self):
        for i in range(len(self.field)):
            for j in range(len(self.field[0])):
                if self._is_done_pos(i, j):
                    return True

    def get_unset_nums(self):
        unchecked = []
        for i, row in enumerate(self.checked):
            for col, check in enumerate(self.checked[i]):
                if not check:
                    unchecked.append(self.field[i][col])

        return unchecked

    def score(self):
        return sum(self.get_unset_nums()) * self.last_num


fields: list[list[str]] = [values[f:f + 5] for f in range(0, len(values), 5)]
fields: list[Board] = [Board(field) for field in fields]



def part_1(numbers, fields):
    for num in numbers:
        for index, board in enumerate(fields):
            if board.set(num):
                print(board.score())
                return


part_1(numbers, fields)


fields: list[list[str]] = [values[f:f + 5] for f in range(0, len(values), 5)]
fields: list[Board] = [Board(field) for field in fields]

def part_2(numbers, boards):
    last = None
    for num in numbers:
        for index, board in enumerate(boards):
            if board.is_done():
                continue
            if board.set(num):
                last = board
    print(last.score())

part_2(numbers, fields)
