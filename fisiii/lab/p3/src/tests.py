import regression  # <- I IMPORT MY PROGRAM
# THE FOLLOWING ARE FOUR TESTS
# I CALL MY FUNCTIONS ON THE FOLLOWING DATA:
proxy_estimate = [130, 650, 99, 150, 128, 302, 95, 945, 368, 961]
plan_A_M = [163, 765, 141, 166, 137, 355, 136, 1206, 433, 1130]
actual_A_M = [186, 699, 132, 272, 291, 331, 199, 1890, 788, 1601]
dev_hours = [15, 69.9, 6.5, 22.4, 28.4, 65.9, 19.4, 198.7, 38.8, 138.2]
# FOUR (4) TESTS:
# TEST THE FIRST (TEST 1):
print("Test 1:", 
    "b_0: %.2f, b_1: %.4f " % regression.lin_reg(
        proxy_estimate, actual_A_M),
    "r_xy: %.4f, r_2: %.4f" % regression.cor_coef(proxy_estimate, actual_A_M),
    "y_k: %.3f" % regression.y_k(386, proxy_estimate, actual_A_M)
)
# TEST THE SECOND (TEST 2):
print("Test 2:", 
    "b_0: %.3f, b_1: %.4f " % regression.lin_reg(
        proxy_estimate, dev_hours),
    "r_xy: %.4f, r_2: %.4f" % regression.cor_coef(proxy_estimate, dev_hours),
    "y_k: %.3f" % regression.y_k(386, proxy_estimate, dev_hours)
)
# TEST THE THIRD (TEST 3):
print("Test 3:", 
    "b_0: %.2f, b_1: %.5f " % regression.lin_reg(
        plan_A_M, actual_A_M),
    "r_xy: %.4f, r_2: %.4f" % regression.cor_coef(plan_A_M, actual_A_M),
    "y_k: %.4f" % regression.y_k(386, plan_A_M, actual_A_M)
)
# TEST THE FOURTH (TEST 4):
print("Test 4:", 
    "b_0: %.3f, b_1: %.6f " % regression.lin_reg(
        plan_A_M, dev_hours),
    "r_xy: %.4f, r_2: %.4f" % regression.cor_coef(plan_A_M, dev_hours),
    "y_k: %.4f" % regression.y_k(386, plan_A_M, dev_hours)
)

