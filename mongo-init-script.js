db = db.getSiblingDB("admin");
db.auth("root", "root");

db = db.getSiblingDB("inventory_db");

db.inventory.insertMany(
    [
        {
            "_id": ObjectId("66d6797e3b8b2b437c89f37a"),
            "quantity": 20,
            "status": "available",
            "lastUpdated": ISODate("2024-09-02T12:00:00.000Z"),
            "productId": "66d675ab3b8b2b437c89f369"
        },
        {
            "_id": ObjectId("66d679c33b8b2b437c89f37f"),
            "quantity": 20,
            "status": "available",
            "lastUpdated": ISODate("2024-09-02T12:00:00.000Z"),
            "productId": "66d6766e3b8b2b437c89f36d"
        },
        {
            "_id": ObjectId("66d679f63b8b2b437c89f382"),
            "quantity": 20,
            "status": "available",
            "lastUpdated": ISODate("2024-09-02T12:00:00.000Z"),
            "productId": "66d676893b8b2b437c89f36e"
        },
        {
            "_id": ObjectId("66d67a103b8b2b437c89f383"),
            "quantity": 20,
            "status": "available",
            "lastUpdated": ISODate("2024-09-02T12:00:00.000Z"),
            "productId": "66d676943b8b2b437c89f36f"
        }
    ]
);

db.products.insertMany(
    [
        {
            "_id": ObjectId("66d675ab3b8b2b437c89f369"),
            "name": "iPhone11",
            "description": "iPhone11",
            "category": "Cellphone",
            "price": 30000,
            "createAt": ISODate("2024-09-02T12:00:00.000Z"),
            "updatedAt": ISODate("2024-09-02T12:00:00.000Z")
        },
        {
            "_id": ObjectId("66d6766e3b8b2b437c89f36d"),
            "name": "iPhone12",
            "description": "iPhone12",
            "category": "Cellphone",
            "price": 31000,
            "createAt": ISODate("2024-09-02T12:00:00.000Z"),
            "updatedAt": ISODate("2024-09-02T12:00:00.000Z")
        },
        {
            "_id": ObjectId("66d676893b8b2b437c89f36e"),
            "name": "iPhone13",
            "description": "iPhone13",
            "category": "Cellphone",
            "price": 33000,
            "createAt": ISODate("2024-09-02T12:00:00.000Z"),
            "updatedAt": ISODate("2024-09-02T12:00:00.000Z")
        },
        {
            "_id": ObjectId("66d676943b8b2b437c89f36f"),
            "name": "iPhone14",
            "description": "iPhone14",
            "category": "Cellphone",
            "price": 35000,
            "createAt": ISODate("2024-09-02T12:00:00.000Z"),
            "updatedAt": ISODate("2024-09-02T12:00:00.000Z")
        }
    ]
);

db.createCollection("inventory_history");