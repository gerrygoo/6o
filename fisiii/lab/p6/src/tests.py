from t_simpsons import t_search

test_cases = [
    {'p': 0.2, "dof": 6},
    {'p': 0.45, "dof": 15},
    {'p': 0.495, "dof": 4}
]

for i, case in enumerate(test_cases):
    print(
        f'Test {i+1}: ',
        f'p={case["p"]} dof={case["dof"]}',
        f'Actual Value = \t{t_search(case["p"], case["dof"]):.5f}'
    )
    