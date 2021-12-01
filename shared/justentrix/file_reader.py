from os.path import join, dirname

inputs_dir = join(dirname(__file__), 'inputs')

def read_file(file_name):
  return open(f'{inputs_dir}/{file_name}', 'r').read()

def read_lines(file_name):
  content = read_file(file_name)
  return content.splitlines()

def read_numbers(file_name):
  lines = read_lines(file_name)
  return list(map(int, lines))
