values = [e.strip() for e in open("puzzle.txt") if e.strip()]


def common_bit(val: list[str], index: int, method: str = "most") -> int:
    zero_common = sum([int(e[index]) for e in val]) < len(val) / 2
    return (0 if zero_common else 1) if method == "most" else (1 if zero_common else 0)


def calc_bits(val: list[str], method: str = "most") -> str:
    return "".join([str(common_bit(val, i, method)) for i in range(len(val[0]))])


print(f"Part one {int(calc_bits(values, 'most'), 2) * int(calc_bits(values, 'least'), 2)}")


def bit_filter(val: list[str], index: int, method: str):
    if len(val) == 1:
        return val[0]
    bit = common_bit(val, index, method)
    return bit_filter([e for e in val if int(e[index]) == bit], index + 1, method)


print(f"Part two {int(bit_filter(values, 0, 'most'), 2) * int(bit_filter(values, 0, 'least'), 2)}")
