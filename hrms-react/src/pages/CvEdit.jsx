import { ErrorMessage, Field, Form, Formik } from 'formik';
import React, { useState, useEffect } from 'react'
import { Button, Container, FormField, Image, Label } from 'semantic-ui-react';
import * as Yup from 'yup';
import HrmsTextInput from '../utilities/customFormControls/HrmsTextInput';
import JobPositionService from '../services/jobPositionService';
import CvService from '../services/cvService';
import ImageService from '../services/imageService';
import HrmsInput from '../utilities/customFormControls/HrmsInput';

export default function CvAdd() {

    let cvService = new CvService()
    let imageService = new ImageService()

    const [state, setState] = useState({ file: null })

    const [cv, setCv] = useState([])

    useEffect(() => {
        cvService.getCv(19).then(result => setCv(result.data.data))
    }, [])


    function onChange(e) {
        console.log(state.file)
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

    const schemaImage = Yup.object({
        image: Yup.string().required("Resim seçilmedi")
    })

    function formatDate(date) {
        date = String(date).split('T');
        return date[0]
    }

    const languageId = {
        id: null
    }

    const programId = {
        id: null
    }

    const schoolId = {
        id: null
    }

    const workExperienceId = {
        id: null
    }

    function handleLanguageId(id) {
        languageId.id = id
    }

    function handleProgramId(id) {
        programId.id = id
    }

    function handleSchoolId(id) {
        schoolId.id = id
    }

    function handleWorkExperienceId(id) {
        workExperienceId.id = id
    }


    return (
        <div>



            <Image src={cv.image != null ? cv.image.imageURL : 'logo192.png'} size='medium' centered />



            <Container className="top">
                <form onSubmit={onFormSubmit}>
                    <h1>Resim Yükle</h1>
                    <input type="file" onChange={onChange} />
                    <button type="submit">Yükle</button>
                </form>
            </Container>

         {console.log(cv)}

            <Container className="top" />
            <h4>Bilgileri Güncelle</h4>
            <Formik
                validationSchema={Yup.object({
                    description: Yup.string("Geçersiz açıklama"),
                    githubAdress: Yup.string("Geçersiz github adresi"),
                    linkedinAdress: Yup.string("Geçersiz linkedin adresi"),
                })}
                initialValues={ {
                    description: cv.description, githubAdress: cv.githubAdress, linkedinAdress: cv.linkedinAdress
                }}
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
                        <input name="description" placeholder="Açıklama" defaultValue={cv.description} onChange={formik.handleChange} style={ {marginBottom:15}}></input>
                        <input name="githubAdress" placeholder="Github adresi" defaultValue={cv.githubAdress} onChange={formik.handleChange} style={ {marginBottom:15}}></input>
                        <input name="linkedinAdress" placeholder="Linkedin adresi" defaultValue={cv.linkedinAdress} onChange={formik.handleChange} style={ {marginBottom:15}} ></input>
                        <Button color="green" type="submit" >Kaydet</Button>
                    </Form>
                )}

            </Formik>

            <Container className="top" />

            <h4>Bilinen Dilleri Güncelle</h4>
            

            {
                cv.language != null ?
                    cv.language.map(language => {
                        return (

                            <Formik key={language.id}
                                initialValues={{
                                    languageLevel: language.languageLevel, languageName: language.languageName
                                }}
                                validationSchema={Yup.object({
                                    languageLevel: Yup.number("Geçersiz dil seviyesi").max(5).positive().required("Dil seviyesi zorunlu"),
                                    languageName: Yup.string("Geçersiz dil ismi").required("Dil adı zorunlu")
                                })}
                                enableReinitialize
                                onSubmit={(values) => {
                                    const cv = {
                                        language: [{ id: languageId.id, languageLevel: values.languageLevel, languageName: values.languageName }]
                                    }
                                    cvService.addCv(19, cv)
                                }}
                            >
                                {formik => (
                                    <Form className="ui form">
                                        <HrmsTextInput name="languageName" placeholder="Dil adı"  ></HrmsTextInput>
                                        <HrmsTextInput name="languageLevel" placeholder="Dil seviyesi [0-5]"  ></HrmsTextInput>
                                        <Button color="green" type="submit" id={language.id} onClick={() => handleLanguageId(language.id)} style={ {marginBottom:15}} >Kaydet</Button>
                                    </Form>
                                )}
                            </Formik>
                        )
                    }
                    )
                    : ""
            }

            <Container className="top" />

            <h4>Bilinen programlama dili veya teknolojilerini güncelle</h4>
            {
                cv.programmingLanguageOrTechnology != null ?
                    cv.programmingLanguageOrTechnology.map(program => {
                        return (
                            <Formik key={program.id}
                                initialValues={{
                                    programName: program.name
                                }}
                                validationSchema={Yup.object({
                                    programName: Yup.string("Geçersiz programlama dili").required("İsim geçersiz")
                                })}
                                onSubmit={(values) => {

                                    const cv = {
                                        programmingLanguageOrTechnology: [{ id: programId.id, name: values.programName }]
                                    }
                                    cvService.addCv(19, cv)
                                }}
                            >
                                {formik => (
                                    <Form className="ui form">
                                        <HrmsTextInput name="programName" placeholder="Programlama dili veya teknolojisi" ></HrmsTextInput>
                                        <Button color="green" type="submit" id={program.id} onClick={() => handleProgramId(program.id)} style={ {marginBottom:15}} >Kaydet</Button>
                                    </Form>
                                )}
                            </Formik>
                        )
                    }
                    )
                    : ""
            }

            <Container className="top" />

            <h4>Okulları Güncelle</h4>
            {cv.school != null ? cv.school.map(school => {
                return (

                    <Formik Formik key={school.id}
                        initialValues={{
                            department: school.department, graduationDate: school.graduationDate != null ?formatDate( school.graduationDate) : undefined, schoolEntryDate: formatDate(school.schoolEntryDate), schoolName: school.schoolName
                        }
                        }
                        validationSchema={Yup.object({
                            department: Yup.string("Geçersiz bölüm adı").required("Bölüm adı zorunlu"),
                            graduationDate: Yup.date("Geçersiz mezuniyet tarihi"),
                            schoolEntryDate: Yup.date("Geçersiz giriş tarihi").required("Okula giriş tarihi zorunlu"),
                            schoolName: Yup.string("Geçersiz okul adı").required("Okul adı zorunlu")
                        })}
                        onSubmit={(values) => {

                            const cv = {
                                school: [{
                                    id: schoolId.id,
                                    department: values.department,
                                    schoolName: values.schoolName,
                                    schoolEntryDate: values.schoolEntryDate,
                                    graduationDate: values.graduationDate
                                }]
                            }
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
                                <Button color="green" type="submit" onClick={() => handleSchoolId(school.id)} style={ {marginBottom:15}} >Kaydet</Button>
                            </Form>
                        )}

                    </Formik >

                )
            }
            )
                : ""
            }

            <Container className="top" />

            <h4>İş Tecrübelerini Güncelle</h4>
            {cv.workExperience != null ? cv.workExperience.map(workExperience => {
                return (
            <Formik Formik key={workExperience.id}
                initialValues={{businessName: workExperience.businessName, dateOfStart: formatDate(workExperience.dateOfStart), jobPositionId: workExperience.jobPosition.id, quitDate: workExperience.quitDate!=null?formatDate(workExperience.quitDate):undefined}}
                validationSchema={Yup.object({
                    businessName: Yup.string("Geçersiz işyeri adı").required("İş yeri adı zorunlu"),
                    dateOfStart: Yup.date("Geçersiz işe geriş tarihi").required("İşe giriş tarihi zorunlu"),
                    jobPositionId: Yup.number("Geçersiz iş posizyonu").required("İş pozisyonu zorunlu"),
                    quitDate: Yup.date("Geçersiz çıkış tarihi")
                })}
                onSubmit={(values) => {
                    const cv = {
                        workExperience: [{
                            id:workExperienceId.id,
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
                        <FormField name="jobPositionId" control="select" onChange={formik.handleChange} defaultValue={workExperience.jobPosition.id}>
                            {jobPositions.map(jobPosition => (<option key={jobPosition.id} value={jobPosition.id}>{jobPosition.name}</option>))}
                        </FormField>
                        İşe başlama tarihi
                        <HrmsTextInput name="dateOfStart" type="date" ></HrmsTextInput>
                        İşten ayrılma tarihi
                        <HrmsTextInput name="quitDate" type="date"></HrmsTextInput>
                        <Button color="green" type="submit" style={ {marginBottom:15}} onClick={()=>handleWorkExperienceId(workExperience.id)} >Kaydet </Button>
                        
                    </Form>
                )}

            </Formik >

            )
            }
            )
                : ""
            }


        </div >
    )
}
