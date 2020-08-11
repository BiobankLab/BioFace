conn = new Mongo();
db = conn.getDB("bioface");
db.getSiblingDB("bioface");
db.createCollection("dictionaries");
db.dictionaries.remove({});
db.dictionaries.insertMany([
{ "name": "Sex", "values": ["Male", "Female", "Unknown", "Undifferentiated"] },
{ "name": "Material type", "values": ["Blood", "DNA", "Faeces", "Immortalized Cell Lines", "Isolated Pathogen", "Other", "Plasma", "RNA", "Saliva", "Serum", "Tissue (Frozen)", "Tissue (FFPE)", "Urine"] },
{ "name": "Storage temperature", "values": ["RT", "2 °C to 10°C", "-18 °C to -35 °C", "-60 °C to -85 °C", "LN", "Other, specify"] },
{ "name": "Data Categories", "values": ["Biological samples", "Survey data", "Imaging data", "Medical records", "National registries", "Genealogical records", "Physiological/Biochemical measurements", "Other"] },
{ "name": "Collection type", "values": ["Case-control", "Cohort", "Cross-sectional", "Longitudinal", "Twin-study", "Quality control", "Population-based", "Disease specific", "Birth cohort", "Other"] },
{ "name": "Inclusion Criteria", "values": ["Health status", "Hospital patient", "Use of medication", "Gravidity", "Age group", "Familial status", "Sex", "Country of residence", "Ethnic origin", "Population representative sampling", "Lifestyle/Exposure", "Other"] },
{ "name": "Omics Technology", "values": ["Genomics", "Transcriptomics", "Proteomics", "Metabolomics", "Other"] },
{ "name": "Ethnic group", "values": ["African", "Caucasian", "Pacific Islander", "East Asian", "Native American", "Other"] },
{ "name": "Permission level", "values": ["Public", "Protected", "Private"] },
{ "name": "Blood group", "values": ["0-", "0+", "A-", "A+", "B-", "B+", "AB-", "AB+"] }
]);
