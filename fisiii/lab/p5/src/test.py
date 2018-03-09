from t_simpsons import t_simpsons

test_cases = [
    {'x': 1.1, "dof": 9},
    {'x': 1.1812, "dof": 10},
    {'x': 2.750, "dof": 30}
]

for i, case in enumerate(test_cases):
    print(
        f'Test {i+1}:\t',
        f'from 0 to x={case["x"]}\tdof  = {case["dof"]}\t',
        f'result = {t_simpsons(case["x"], case["dof"]):.5f}'
    )
    