import React from 'react'
import JobSeekerService from '../services/jobSeekerService'
import * as Yup from 'yup';
import { Form, Formik } from 'formik';
import HrmsTextInput from '../utilities/customFormControls/HrmsTextInput';
import { Button } from 'semantic-ui-react';

export default function JobSeekerAdd() {
    let jobSeekerService = new JobSeekerService()

    const initialValues = {
        birthDay: "", name: "", lastName: "",
        nationalIdentity: "", email: "",
        password: "", passwordRepeat: ""
    }

    const schema = Yup.object({
        birthDay: Yup.date("Doğum tarihi formatında veri girilmelidir").required("Doğum tarihi zorunlu"),
        name: Yup.string("Geçersiz isim").required("İsim zorunlu"),
        lastName: Yup.string("Geçersiz soyad").required("Soyad zorunlu"),
        nationalIdentity: Yup.number("Geçersiz TC kimlik numarası").positive("Negatif değer olamaz").required("TC kimlik numarası zorunlu").typeError("Geçersiz TC kimlik numarası"),
        email: Yup.string("Geçersiz e-mail").email("Geçersiz e-mail adresi").required("E-mail adresi zorunlu"),
        password: Yup.string("Geçersiz şifre").required("Şifre zorunlu"),
        passwordRepeat: Yup.string("Geçersiz şifre").oneOf([Yup.ref("password"),null],"Şifre ve şifre tekrar uyuşmuyor").required("Şifre tekrar zorunlu")

    })
    return (
        <div>
            <Formik
                initialValues={initialValues}
                validationSchema={schema}
                onSubmit={(values) => {

                    

                    const jobSeeker = {
                        birthDay:values.birthDay,
                        lastName:values.lastName,
                        member:{email:values.email,password:values.password,passwordRepeat:values.passwordRepeat},
                        name:values.name,
                        nationalIdentity:values.nationalIdentity
                    }
                    console.log(jobSeeker)
                    jobSeekerService.addJobSeeker(jobSeeker)
                }}
            >
                {formik => (
                    <Form className="ui form">
                        <HrmsTextInput name="birthDay" placeholder="Doğum tarihi" type="date"></HrmsTextInput>
                        <HrmsTextInput name="name" placeholder="İsim"></HrmsTextInput>
                        <HrmsTextInput name="lastName" placeholder="Soyad"></HrmsTextInput>
                        <HrmsTextInput name="nationalIdentity" placeholder="TC kimlik numarası" type="number" ></HrmsTextInput>
                        <HrmsTextInput name="email" placeholder="E-mail adresi"></HrmsTextInput>
                        <HrmsTextInput name="password" placeholder="Şifre" type="password"></HrmsTextInput>
                        <HrmsTextInput name="passwordRepeat" placeholder="Şifre tekrar" type="password"></HrmsTextInput>
                        <Button color="green" type="submit" >Üye ol</Button>
                    </Form>
                )}
            </Formik>
        </div>
    )
}
