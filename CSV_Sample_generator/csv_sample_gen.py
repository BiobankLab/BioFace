#!/usr/bin/env python
# -*- coding: utf-8 -*-
import csv
import uuid
import random
from datetime import *
import sys

if len(sys.argv) != 4:
    print("Wrong arguments count. Press any key to quit")
    input()
    sys.exit(0)

filename = str(sys.argv[1]) + ".csv"

no_data_symbol = "ND"

headers = [
    'LP',
    'ID probanta',
    'Płeć',
    'Data urodzenia',
    'Disease I',
    'Data of Disease I',
    'Disease II',
    'Data of Disease II',
    'Kolor oczu',
    'Blood group',
    'Kolor Włosów',
    'Material I',
    'Data pobrania materiału I',
    'Materiał II',
    'Data pobrania materiału II',
    'Kwestionariusz antropologiczny',
    'Kwestionariusz SES',
    'Kwestionariusz psychologiczny',
    'Kwestionariusz nałogi',
    'ELSI',
    'Access',
    'Collection ID',
    'Institution',
    'Region of Origin'
]

sex_values = [
    'Male',
    'Female',
    'Unknown',
    'Undifferentiated'
]

dieseases_values = [
    'Acute liver failure',
    'Alcoholic fatty liver disease',
    'Alcoholic hepatitis',
    'Alcoholic cirrhosis',
    'Hepatitis B',
    'Hepatitis C',
    'Adenoid Cystic Carcinoma',
    'Adrenal Gland Tumor',
    'Amyloidosis',
    'Anal Cancer',
    'Appendix Cancer',
    'Astrocytoma - Childhood',
    'Ataxia-Telangiectasia',
    'Beckwith-Wiedemann Syndrome',
    'Bile Duct Cancer (Cholangiocarcinoma)',
    'Birt-Hogg-Dubé Syndrome',
    'Bladder Cancer',
    'Bone Cancer',
    'Brain Stem Glioma - Childhood',
    'Brain Tumor',
    'Breast Cancer',
    'Breast Cancer - Inflammatory',
    'Breast Cancer - Metastatic',
    'Breast Cancer in Men',
    'Carcinoid Tumor',
    'Carney Complex',
    'Central Nervous System Tumors - Childhood',
    'Cervical Cancer',
    'Childhood Cancer',
    'Colorectal Cancer',
    'Cowden Syndrome',
    'Craniopharyngioma - Childhood',
    'Desmoplastic Infantile Ganglioglioma, Childhood Tumor',
    'Ependymoma - Childhood',
    'Esophageal Cancer',
    'Ewing Sarcoma - Childhood and Adolescence',
    'Eye Cancer',
    'Eyelid Cancer',
    'Familial Adenomatous Polyposis',
    'Familial GIST',
    'Familial Malignant Melanoma',
    'Familial Non-VHL Clear Cell Renal Cell Carcinoma',
    'Familial Pancreatic Cancer',
    'Gallbladder Cancer',
    'Gastrointestinal Stromal Tumor - GIST',
    'Germ Cell Tumor - Childhood',
    'Gestational Trophoblastic Disease',
    'Head and Neck Cancer',
    'Hereditary Breast and Ovarian Cancer',
    'Hereditary Diffuse Gastric Cancer',
    'Hereditary Leiomyomatosis and Renal Cell Cancer',
    'Hereditary Mixed Polyposis Syndrome',
    'Hereditary Pancreatitis',
    'Hereditary Papillary Renal Carcinoma',
    'HIV/AIDS-Related Cancer',
    'Juvenile Polyposis Syndrome',
    'Kidney Cancer',
    'Lacrimal Gland Tumorv',
    'Laryngeal and Hypopharyngeal Cancer',
    'Leukemia - Acute Lymphoblastic - ALL - Childhood',
    'Leukemia - Acute Lymphocytic - ALL',
    'Leukemia - Acute Myeloid - AML',
    'Leukemia - Acute Myeloid - AML - Childhood',
    'Leukemia - B-cell Prolymphocytic Leukemia and Hairy Cell Leukemia',
    'Leukemia - Chronic Lymphocytic - CLL',
    'Leukemia - Chronic Myeloid - CML',
    'Leukemia - Chronic T-Cell Lymphocytic',
    'Leukemia - Eosinophilic',
    'Li-Fraumeni Syndrome',
    'Liver Cancer',
    'Lung Cancer - Non-Small Cell',
    'Lung Cancer - Small Cell',
    'Lymphoma - Hodgkin',
    'Lymphoma - Hodgkin - Childhood',
    'Lymphoma - Non-Hodgkin',
    'Lymphoma - Non-Hodgkin - Childhood',
    'Lynch Syndrome',
    'Mastocytosis',
    'Medulloblastoma - Childhood',
    'Melanoma',
    'Meningioma',
    'Mesothelioma',
    'Muir-Torre Syndrome',
    'Multiple Endocrine Neoplasia Type 1',
    'Multiple Endocrine Neoplasia Type 2',
    'Multiple Myeloma',
    'Myelodysplastic Syndromes - MDS',
    'MYH-Associated Polyposis',
    'Nasal Cavity and Paranasal Sinus Cancer',
    'Nasopharyngeal Cancer',
    'Neuroblastoma - Childhood',
    'Neuroendocrine Tumor',
    'Neuroendocrine Tumor of the Pancreas',
    'Neurofibromatosis Type 1',
    'Neurofibromatosis Type 2',
    'Nevoid Basal Cell Carcinoma Syndrome',
    'Oral and Oropharyngeal Cancer',
    'Osteosarcoma - Childhood and Adolescence',
    'Ovarian, Fallopian Tube, and Peritoneal Cancer',
    'Pancreatic Cancer',
    'Parathyroid Cancer',
    'Penile Cancer',
    'Peutz-Jeghers Syndrome',
    'Pituitary Gland Tumor',
    'Pleuropulmonary Blastoma - Childhood',
    'Prostate Cancer',
    'Retinoblastoma - Childhood',
    'Rhabdomyosarcoma - Childhood',
    'Salivary Gland Cancer',
    'Sarcoma - Kaposi',
    'Sarcoma, Soft Tissue',
    'Sarcomas of Specific Organs',
    'Skin Cancer (Non-Melanoma)',
    'Small Bowel Cancer',
    'Stomach Cancer',
    'Testicular Cancer',
    'Thymoma and Thymic Carcinoma',
    'Thyroid Cancer',
    'Tuberous Sclerosis Complex',
    'Unknown Primary',
    'Uterine Cancer',
    'Vaginal Cancer',
    'Von Hippel-Lindau Syndrome',
    'Vulvar Cancer',
    'Waldenstrom’s Macroglobulinemia',
    'Werner Syndrome',
    'Wilms Tumor - Childhood',
    'Xeroderma Pigmentosum',
    'Abnormal Heart Rhythms',
    'Coronary Artery Disease',
    'Heart Failure',
    'Heart Valve Disease',
    'Congenital Heart Disease',
    'Cardiomyopathies',
    'Pericarditis',
    'Aorta Disease and Marfan Syndrome',
    'Other Vascular Disease',
    'Type 1 diabetes',
    'Type 2 diabetes',
    'Gestational diabetes',
    'Bacteria infection',
    'Virtus infection',
    'Fungi',
    'Protozoa',
    'Parasites',
    'Stroke',
    'Multiple Sclerosis',
    'Parkinsons Disease',
    'Traumatic Brain Injury',
    'Spinal Cord Injury',
    'Dystonia',
    'Chronic Regional Pain Syndrome',
    'Motor Neuron Disease',
    'Amyotrophic Lateral Sclerosis',
    'Intellectual disability',
    'Global developmental delay',
    'Communication disorders',
    'Autism spectrum disorder',
    'Attention-deficit hyperactivity disorder',
    'Bipolar disorder',
    'Mania',
    'Depressive episodes',
    'Generalized anxiety disorder',
    'Agoraphobia',
    'Social anxiety disorder',
    'Specific phobias',
    'Panic disorder',
    'Separation anxiety disorder',
    'Acute stress disorder',
    'Adjustment disorders',
    'Post-traumatic stress disorder'
]

blood_group_values = [
    'A-',
    'B-',
    'AB-',
    '0-',
    'A+',
    'B+',
    'AB+',
    '0+'
]

hair_color_values = [
    'A',
    'B',
    'C',
    'D',
    'E',
    'F',
    'G',
    '1',
    'H',
    'O',
    'M'
]

material_type_values = [
    'Blood',
    'DNA',
    'Faeces',
    'Immortalized Cell Lines',
    'Isolated Pathogen',
    'Other',
    'Plasma',
    'RNA',
    'Saliva',
    'Serum',
    'Tissue (Frozen)',
    'Tissue (FFPE)',
    'Urine'
]

boolean_values = [
    'YES',
    'NO'
]

elsi_values = [
    'Recontact needed',
    'Dynamic consent',
    'Broad consenrt'
]

access_values = [
    'Public',
    'Protected',
    'Private'
]

region_of_origin_values = [
    'Poland/Europe',
    'Europe',
    'Africa',
    'Germany/Europe',
    'Mazowieckie/Poland/Europe',
    'Slaskie/Poland/Europe',
    'Lodzkie/Poland/Europe',
    'LODZ/POLAND/EUROPE',
    'USA/North America',
    'India/Asia',
    'Spain/Europe',
    'WROCLAW/POLAND/EUROPE',
    'KRAKOW/POLAND/EUROPE',
    'GDANSK/POLAND/EUROPE',
    'LUBLIN/POLAND/EUROPE'
    'BIALYSTOK/POLAND/EUROPE',
    'SLUPSK/POLAND/EUROPE',
    'POZNAN/POLAND/EUROPE'
]

collection_values = []


def generate_collections():
    for i in range(0, int(int(sys.argv[2]) / 10)):
        collection_values.append(str(uuid.uuid1()))


def execute_with_nd_possibility(func):
    random_number = random.randint(0, 10)
    if random_number < 3:
        return "ND"
    else:
        return func


def random_table_value(table):
    random_number = random.randint(0, len(table) - 1)
    return table[random_number]


def random_date_for_years_back(years):
    date = datetime.today() - timedelta(days=random.randint(0, 365 * years))

    if date.day < 10:
        date_str = '0' + str(date.day)
    else:
        date_str = str(date.day)

    date_str += '-'

    if date.month < 10:
        date_str += '0' + str(date.month)
    else:
        date_str += str(date.month)

    date_str += '-'

    date_str += str(date.year)

    return date_str


with open(filename, 'w') as csvfile:
    generate_collections()
    csvwriter = csv.writer(csvfile, delimiter=',',
                           quotechar='|', quoting=csv.QUOTE_MINIMAL)
    csvwriter.writerow(headers)
    for i in range(1, int(sys.argv[2]) + 1):
        lp = i
        probant_id = str(uuid.uuid1())
        sex = random_table_value(sex_values)
        birth_date = execute_with_nd_possibility(random_date_for_years_back(100))
        disease_1 = execute_with_nd_possibility(random_table_value(dieseases_values))
        date_of_disease_1 = execute_with_nd_possibility(random_date_for_years_back(100))
        disease_2 = execute_with_nd_possibility(random_table_value(dieseases_values))
        date_of_disease_2 = execute_with_nd_possibility(random_date_for_years_back(100))
        eye_color = execute_with_nd_possibility(random.randint(1, 16))
        blood_group = execute_with_nd_possibility(random_table_value(blood_group_values))
        hair_color = execute_with_nd_possibility(random_table_value(hair_color_values))
        material_1 = execute_with_nd_possibility(random_table_value(material_type_values))
        date_of_material_1 = execute_with_nd_possibility(random_date_for_years_back(10))
        material_2 = execute_with_nd_possibility(random_table_value(material_type_values))
        date_of_material_2 = execute_with_nd_possibility(random_date_for_years_back(10))
        antropological_questionnaire = random_table_value(boolean_values)
        ses_questionnaire = random_table_value(boolean_values)
        psychological_questionnaire = random_table_value(boolean_values)
        addiction_questionnaire = random_table_value(boolean_values)
        elsi = execute_with_nd_possibility(random_table_value(elsi_values))
        access = random_table_value(access_values)
        name_of_project = random_table_value(collection_values)
        institution = sys.argv[3]
        region_of_origin = execute_with_nd_possibility(random_table_value(region_of_origin_values))
        csvwriter.writerow([str(lp), str(probant_id), str(sex), str(birth_date), str(disease_1).replace(',', ''), str(date_of_disease_1),
                            str(disease_2).replace(',', ''),
                            str(date_of_disease_2), str(eye_color), str(blood_group), str(hair_color), str(material_1),
                            str(date_of_material_1), str(material_2), str(date_of_material_2),
                            str(antropological_questionnaire), str(ses_questionnaire), str(psychological_questionnaire),
                            str(addiction_questionnaire),
                            str(elsi), str(access), str(name_of_project), str(institution), str(region_of_origin)])
