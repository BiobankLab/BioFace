conn = new Mongo();
db = conn.getDB("bioface");
db.getSiblingDB("bioface");
biobank = {name: "Lodz"};
db.testData.insert( biobank );
