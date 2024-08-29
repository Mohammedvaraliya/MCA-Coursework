# Case: Hospital Management System

## Aim:

XYZ hospital is a multi specialty hospital that includes a number of departments, rooms, doctors, nurses, compounders, and other staff working in the hospital. Patients having different kinds of ailments come to the hospital and get checkup done from the concerned doctors. If required, they are admitted in the hospital and discharged after treatment. The aim of this case study is to design and develop a database for the hospital to maintain the records of various department, rooms, and doctors in the hospital. It also maintain record of the regular patients, patients admitted in the hospital, the check up of patients done by the doctors, patients that have been operated, and patients discharged from the hospital.

## Description:

In hospital, there are many departments like Orthopedic, Pathology, Emergency, Dental, Gynaecology, Anesthetic, I.C.U., Blood Bank, Operation Theater, Laboratory, M.R.I., Neurology, Cardiology, Cancer Department, Corpse, etc. There is an OPD where patients come and get a card (that is, entry card of the patient) for check up from the concerned doctor. After making entry in the card, they go to the concerned doctor's room and the doctor checks up their ailments. According to the ailments, the doctor either prescribes medicine or admits the patient in the concerned department. The patient may choose either private or general room according to his/her need. But before getting admission in the hospital, the patient has to fulfill certain formalities of the hospital like room charges, etc. After the treatment is completed, the doctor discharges the patient. Before discharging from the hospital, the patient again has to complete certain formalities of the hospital like balance charges, test charges operation charges (if any), blood charges, doctor's charges etc. Next we talk about the doctors of the hospital. There are two types of the doctors in the hospital, namely, regular doctors and call on doctors. Regular doctors are those doctors who come to the hospital daily. Calls on doctors are those doctors who are called by the hospital if the concerned doctor is not available.

### Identify the Entities:

1.  **Hospital**
2.  **Department**
3.  **Room**
4.  **Doctor**
5.  **Patient**
6.  **Admission**
7.  **Checkup**
8.  **Treatment**
9.  **Discharge**

### Identify the Attributes for Each Entity:

1.  **Hospital**: Hospital_ID, Name, Address, Contact_Number
2.  **Department**: Department_ID, Name, Location
3.  **Room**: Room_ID, Room_Type (Private/General), Charges, Status
4.  **Doctor**: Doctor_ID, Name, Specialty, Type (Regular/On-Call), Contact_Number
5.  **Patient**: Patient_ID, Name, Address, Contact_Number, Date_of_Birth, Gender, Entry_Card_Number
6.  **Admission**: Admission_ID, Date, Room_Type, Room_ID, Patient_ID, Doctor_ID
7.  **Checkup**: Checkup_ID, Date, Symptoms, Diagnosis, Doctor_ID, Patient_ID
8.  **Treatment**: Treatment_ID, Treatment_Type, Medicine_Prescribed, Operation_Details, Doctor_ID, Patient_ID
9.  **Discharge**: Discharge_ID, Date, Final_Bill, Patient_ID, Doctor_ID, Treatment_ID

### Identify the Relationships Between Entities:

1. **Hospital to Department:**

- **Entities:** Hospital — Department
- **Relationship Diamond:** **Contains**
- **Relationship Line:** **Hospital** — (1) ⟶ (Contains) ⟶ (Many) — **Department**

2. **Department to Doctor:**

- **Entities:** Department — Doctor
- **Relationship Diamond:** **Employs/Assigns**
- **Relationship Line:** **Department** — (1) ⟶ (Employs/Assigns) ⟶ (Many) — **Doctor**

3. **Department to Room:**

- **Entities:** Department — Room
- **Relationship Diamond:** **Allocates**
- **Relationship Line:** **Department** — (1) ⟶ (Allocates) ⟶ (Many) — **Room**

4. **Patient to Checkup:**

- **Entities:** Patient — Checkup
- **Relationship Diamond:** **Undergoes**
- **Relationship Line:** **Patient** — (1) ⟶ (Undergoes) ⟶ (Many) — **Checkup**

5. **Doctor to Checkup:**

- **Entities:** Doctor — Checkup
- **Relationship Diamond:** **Performs**
- **Relationship Line:** **Doctor** — (1) ⟶ (Performs) ⟶ (Many) — **Checkup**

6. **Patient to Admission:**

- **Entities:** Patient — Admission
- **Relationship Diamond:** **Admits**
- **Relationship Line:** **Patient** — (1) ⟶ (Admits) ⟶ (Many) — **Admission**

7. **Admission to Room:**

- **Entities:** Admission — Room
- **Relationship Diamond:** **Occupies**
- **Relationship Line:** **Admission** — (Many) ⟶ (Occupies) ⟶ (1) — **Room**

8. **Admission to Doctor:**

- **Entities:** Admission — Doctor
- **Relationship Diamond:** **Consults**
- **Relationship Line:** **Admission** — (Many) ⟶ (Consults) ⟶ (1) — **Doctor**

9. **Patient to Treatment:**

- **Entities:** Patient — Treatment
- **Relationship Diamond:** **Receives**
- **Relationship Line:** **Patient** — (1) ⟶ (Receives) ⟶ (Many) — **Treatment**

10. **Doctor to Treatment:**

- **Entities:** Doctor — Treatment
- **Relationship Diamond:** **Administers**
- **Relationship Line:** **Doctor** — (1) ⟶ (Administers) ⟶ (Many) — **Treatment**

11. **Patient to Discharge:**

- **Entities:** Patient — Discharge
- **Relationship Diamond:** **Discharged**
- **Relationship Line:** **Patient** — (1) ⟶ (Discharged) ⟶ (1) — **Discharge**

12. **Treatment to Discharge:**

- **Entities:** Treatment — Discharge
- **Relationship Diamond:** **Finalizes**
- **Relationship Line:** **Treatment** — (Many) ⟶ (Finalizes) ⟶ (1) — **Discharge**

13. **Hospital to Room:**

- **Entities:** Hospital — Room
- **Relationship Diamond:** **Contains**
- **Relationship Line:** **Hospital** — (1) ⟶ (Contains) ⟶ (Many) — **Room**

14. **Hospital to Patient:**

- **Entities:** Hospital — Patient
- **Relationship Diamond:** **Treats**
- **Relationship Line:** **Hospital** — (1) ⟶ (Treats) ⟶ (Many) — **Patient**

15. **Hospital to Doctor:**

- **Entities:** Hospital — Doctor
- **Relationship Diamond:** **Hires/Contracts**
- **Relationship Line:** **Hospital** — (1) ⟶ (Hires/Contracts) ⟶ (Many) — **Doctor**
