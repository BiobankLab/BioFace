1. Install nodejs 
ref: https://nodejs.org/en/

2. Type in console
``` bash
npm install
npm run dev

3. To add more data to charts modify file /src/components/Dashboard.vue and change static data (for now: line 299-320) - add new record (separated by comma)

Actual data:
```bash
data: [
    {
        diseaseType: 'Cancer',
        diseaseDetails: 'Lung cancer',
        sampleType: 'Blood',
        sampleDetails: 'Whole blood',
        quantity: 100,
        date: '12/07/2018'
    }, 
    {
        diseaseType: 'Diebetes',
        diseaseDetails: 'Unspecific',
        sampleType: 'Blood',
        sampleDetails: 'Serum',
        quantity: 650,
        date: '15/07/2018'
    }, 
    {
        diseaseType: 'Cancer',
        diseaseDetails: 'Breast cancer',
        sampleType: 'Cells',
        sampleDetails: 'RBC',
        quantity: 80,
        date: '20/07/2018'
    }
],
