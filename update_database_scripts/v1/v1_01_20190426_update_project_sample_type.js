conn = new Mongo();
db = conn.getDB("bioface");
db.getSiblingDB("bioface");

var cursor = db.project.find();
while (cursor.hasNext()) {
	var project = cursor.next();
	db.project.update({_id: project._id}, {$set: {sampleType: [project.sampleType]}});
}
