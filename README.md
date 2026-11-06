# LabShop daily report

Reads the day's orders from `data/orders.csv` and prints the daily sales report.
The same program is written in Python, Java, C# and Go.

## Run

| Language | Folder | Report | Tests |
|---|---|---|---|
| Python | `python/` | `python report.py ../data/orders.csv` | `python -m unittest -v` |
| Java | `java/` | `mvn -q compile && java -cp target/classes labshop.Report ../data/orders.csv` | `mvn test` |
| C# | `csharp/` | `dotnet run --project LabShop -- ../data/orders.csv` | `dotnet test` |
| Go | `go/` | `go run . ../data/orders.csv` | `go test ./...` |
