"""Prints the LabShop daily report.

    python report.py ../data/orders.csv
"""
import sys

from pricing import Item, Order, find_coupon, price_order

HEADER = "order  customer     subtotal  discount       tax  shipping       total"


def read_orders(path):
    orders = []
    with open(path, encoding="utf-8") as f:
        next(f)  # header
        for line in f:
            line = line.rstrip("\n")
            if line == "":
                continue
            order_id, customer, coupon_code, items = line.split(",")
            order = Order(int(order_id), customer, coupon_code)
            for part in items.split("|"):
                sku, price, qty = part.split(":")
                order.items.append(Item(sku, int(price), int(qty)))
            orders.append(order)
    return orders


def grand_total(orders):
    return sum(price_order(order).total for order in orders)


def main(path):
    orders = read_orders(path)
    print("LabShop daily report")
    print(HEADER)
    invalid = 0
    for order in orders:
        bill = price_order(order)
        print(f"{order.id:5d}  {order.customer:<10} {bill.subtotal:10d} {bill.discount:9d} "
              f"{bill.tax:9d} {bill.shipping:9d} {bill.total:11d}")
        if order.coupon_code != "" and find_coupon(order.coupon_code) is None:
            invalid += 1
    print(f"orders: {len(orders)}")
    print(f"invalid coupons: {invalid}")
    print(f"grand total: {grand_total(orders)}")


if __name__ == "__main__":
    if len(sys.argv) != 2:
        sys.exit("usage: python report.py <orders.csv>")
    main(sys.argv[1])
