import { Form, Formik } from 'formik';
import React, { useState, useEffect } from 'react'
import { Button, Container, FormField } from 'semantic-ui-react';
import * as Yup from 'yup';
import HrmsTextInput from '../utilities/customFormControls/HrmsTextInput';
import JobPositionService from '../services/jobPositionService';
import CvService from '../services/cvService';
import ImageService from '../services/imageService';

export default function CvAdd() {

    let cvService = new CvService()
    let imageService = new ImageService()

    const [state, setState] = useState({ file: null })


    function onChange(e) {
        setState({ file: e.target.files[0] })
    }

    function onFormSubmit(e) {
        e.preventDefault() 
        fileUpload(state.file)
    }

    function fileUpload(file) {
        const formData = new FormData();
        formData.append('file', file)

        return imageService.uploadImage(19, formData)
    }

    const [jobPositions, setJobPositions] = useState([])

    useEffect(() => {
        let jobPositionService = new JobPositionService()
        jobPositionService.getJobPositions().then((result) => setJobPositions(result.data.data))
    }, [])

    const initialValuesImage = {
        image: ""
    }

    const initialValuesInfo = {
        description: "", githubAdress: "", linkedinAdress: "",
    }

    const initialValuesLanguage = {
        languageLevel: "", languageName: ""
    }

    const initialValuesProgram = {
        programName: ""
    }

    const initialValuesSchool = {
        department: "", graduationDate: "", schoolEntryDate: "", schoolName: ""
    }

    const initialValuesWorkExperience = {
        businessName: "", dateOfStart: "", jobPositionId: 2, quitDate: ""
    }

    const schemaImage = Yup.object({
        image: Yup.string().required("Resim seçilmedi")
    })

    const schemaInfo = Yup.object({
        description: Yup.string("Geçersiz açıklama"),
        githubAdress: Yup.string("Geçersiz github adresi"),
        linkedinAdress: Yup.string("Geçersiz linkedin adresi"),
    })

    const schemaLanguage = Yup.object({
        languageLevel: Yup.number("Geçersiz dil seviyesi").max(5).positive().required("Dil seviyesi zorunlu"),
        languageName: Yup.string("Geçersiz dil ismi").required("Dil adı zorunlu")
    })

    const schemaProgram = Yup.object({
        programName: Yup.string("Geçersiz programlama dili").required("İsim geçersiz")
    })

    const schemaSchool = Yup.object({
        department: Yup.string("Geçersiz bölüm adı").required("Bölüm adı zorunlu"),
        graduationDate: Yup.date("Geçersiz mezuniyet tarihi"),
        schoolEntryDate: Yup.date("Geçersiz giriş tarihi").required("Okula giriş tarihi zorunlu"),
        schoolName: Yup.string("Geçersiz okul adı").required("Okul adı zorunlu")
    })

    const schemaWorkExperience = Yup.object({
        businessName: Yup.string("Geçersiz işyeri adı").required("İş yeri adı zorunlu"),
        dateOfStart: Yup.date("Geçersiz işe geriş tarihi").required("İşe giriş tarihi zorunlu"),
        jobPositionId: Yup.number("Geçersiz iş posizyonu").required("İş pozisyonu zorunlu"),
        quitDate: Yup.date("Geçersiz çıkış tarihi")
    })




    return (
        <div>



            <Container className="top">
                <form onSubmit={onFormSubmit}>
                    <h1>Resim Yükle</h1>
                    <input type="file" onChange={onChange} />
                    <button type="submit">Yükle</button>
                </form>
            </Container>



            <Container className="top" />
            Bilgiler
            <Formik
                initialValues={initialValuesInfo}
                validationSchema={schemaInfo}
                onSubmit={(values) => {
                    const cv = {
                        description: values.description,
                        githubAdress: values.githubAdress,
                        linkedinAdress: values.linkedinAdress,
                    }
                    console.log(cv)
                    cvService.addCv(19, cv)

                }}
            >
                {formik => (
                    <Form className="ui form">
                        <HrmsTextInput name="description" placeholder="Açıklama"></HrmsTextInput>
                        <HrmsTextInput name="githubAdress" placeholder="Github adresi"></HrmsTextInput>
                        <HrmsTextInput name="linkedinAdress" placeholder="Linkedin adresi"></HrmsTextInput>
                        <Button color="green" type="submit" >Kaydet</Button>
                    </Form>
                )}

            </Formik>

            <Container className="top" />
            Bilinen dil ekle
            <Formik
                initialValues={initialValuesLanguage}
                validationSchema={schemaLanguage}
                onSubmit={(values) => {
                    const cv = {
                        language: [{ languageLevel: values.languageLevel, languageName: values.languageName }]
                    }
                    console.log(cv)
                    cvService.addCv(19, cv)

                }}
            >
                {formik => (
                    <Form className="ui form">
                        <HrmsTextInput name="languageLevel" placeholder="Dil seviyesi" type="number"></HrmsTextInput>
                        <HrmsTextInput name="languageName" placeholder="Dil adı"></HrmsTextInput>
                        <Button color="green" type="submit" >Dil Ekle</Button>
                    </Form>
                )}

            </Formik>

            <Container className="top" />
            Bilinen programlama dili veya teknolojisi ekle
            <Formik
                initialValues={initialValuesProgram}
                validationSchema={schemaProgram}
                onSubmit={(values) => {
                    const cv = {
                        programmingLanguageOrTechnology: [{ name: values.programName }]
                    }
                    console.log(cv)
                    cvService.addCv(19, cv)
                }}
            >
                {formik => (
                    <Form className="ui form">
                        <HrmsTextInput name="programName" placeholder="Programlama dili veya teknolojisi"></HrmsTextInput>
                        <Button color="green" type="submit" >Ekle</Button>
                    </Form>
                )}

            </Formik>

            <Container className="top" />
            Okul ekle
            <Formik
                initialValues={initialValuesSchool}
                validationSchema={schemaSchool}
                onSubmit={(values) => {

                    const cv = {
                        school: [{
                            department: values.department,
                            schoolName: values.schoolName,
                            schoolEntryDate: values.schoolEntryDate,
                            graduationDate: values.graduationDate
                        }]
                    }
                    console.log(cv)
                    cvService.addCv(19, cv)
                }}
            >
                {formik => (
                    <Form className="ui form">
                        <HrmsTextInput name="department" placeholder="Bölüm adı"></HrmsTextInput>
                        <HrmsTextInput name="schoolName" placeholder="Okul adı"></HrmsTextInput>
                        Okula giriş tarihi
                        <HrmsTextInput name="schoolEntryDate" label="Okula giriş tarihi" type="date"></HrmsTextInput>
                        Mezuniyet tarihi
                        <HrmsTextInput name="graduationDate" placeholder="Okul mezuniyet tarihi" type="date"></HrmsTextInput>
                        <Button color="green" type="submit" >Okul Ekle</Button>
                    </Form>
                )}

            </Formik>
            <Container className="top" />
            İş tecrübesi ekle
            <Formik
                initialValues={initialValuesWorkExperience}
                validationSchema={schemaWorkExperience}
                onSubmit={(values) => {
                    const cv = {
                        workExperience: [{
                            businessName: values.businessName,
                            dateOfStart: values.dateOfStart,
                            quitDate: values.quitDate,
                            jobPosition: { id: values.jobPositionId, }
                        }]
                    }
                    console.log(cv)
                    cvService.addCv(19, cv)
                }}
            >
                {formik => (
                    <Form className="ui form">
                        <HrmsTextInput name="businessName" placeholder="İş yeri adı"></HrmsTextInput>
                        <FormField name="jobPositionId" control="select" onChange={formik.handleChange} >
                            {jobPositions.map(jobPosition => (<option key={jobPosition.id} value={jobPosition.id}>{jobPosition.name}</option>))}
                        </FormField>
                        İşe başlama tarihi
                        <HrmsTextInput name="dateOfStart" type="date"></HrmsTextInput>
                        İşten ayrılma tarihi
                        <HrmsTextInput name="quitDate" type="date"></HrmsTextInput>
                        <Button color="green" type="submit" >İş Tecrübesi Ekle</Button>
                    </Form>
                )}

            </Formik>




        </div>
    )
}
