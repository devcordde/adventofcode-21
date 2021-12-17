import math
from typing import Union

bits = "".join([str(bin(int(char, 16)))[2:].zfill(4) for char in list(open("puzzle.txt").read().strip())])


class Packet:
    types = {
        0: lambda x: sum([e for e in x]),
        1: lambda x: math.prod([e for e in x]),
        2: lambda x: min([e for e in x]),
        3: lambda x: max([e for e in x]),
        4: lambda x: x,
        5: lambda x: int(x[0] > x[1]),
        6: lambda x: int(x[0] < x[1]),
        7: lambda x: int(x[0] == x[1])
    }

    def __init__(self, content):
        self.raw_content = content
        self._content = content
        self.parsed_content = ""
        self.version = self.next_bytes_int(3)
        self.type_id = self.next_bytes_int(3)
        self.value: Union[int, list["Packet"]] = self.parse_content()

    def next_bytes(self, amount) -> str:
        curr_content = self._content[:amount]
        self._content = self._content[amount:]
        self.parsed_content += curr_content
        return curr_content

    def next_bytes_int(self, amount) -> int:
        return int(self.next_bytes(amount), 2)

    def parse_content(self) -> Union[int, list["Packet"]]:
        if self.type_id == 4:
            return self.extract_literal()
        length_type_id = self.next_bytes_int(1)
        iter_range = self.next_bytes_int(15 if not length_type_id else 11)
        sub_packets = []
        if length_type_id == 0:
            parsed = 0
            while parsed < iter_range:
                sub_packets.append(self.sub_packet())
                self.skip_bytes(sub_packets[-1].length())
                parsed += sub_packets[-1].length()
        else:
            for i in range(iter_range):
                sub_packets.append(self.sub_packet())
                self.skip_bytes(sub_packets[-1].length())
        return sub_packets

    def sub_packet(self):
        return self.__class__(self._content)

    def extract_literal(self) -> (int, int):
        groups = []
        while True:
            content = self.next_bytes(5)
            groups.append(content[1:5])
            if content[0] == "0":
                break
        return int("".join(groups), 2)

    def __repr__(self):
        return f"Packet(Version {self.version} | Type {self.type_id} | Value {self.value})"

    def length(self):
        return len(self.parsed_content)

    def skip_bytes(self, num):
        self.next_bytes(num)

    def version_sum(self):
        if isinstance(self.value, int):
            return self.version
        return self.version + sum(e.version_sum() for e in self.value if isinstance(e, Packet))

    def type_value(self):
        if self.type_id == 4:
            return self.value
        return Packet.types[self.type_id]([e.type_value() for e in self.value])


packet = Packet(bits)
print(f"Part one {packet.version_sum()}")
print(f"Part two {packet.type_value()}")
