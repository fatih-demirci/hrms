import { Form, Formik } from 'formik';
import React, { useState } from 'react'
import { Button, Message } from 'semantic-ui-react';
import * as Yup from 'yup';
import EmployerService from '../services/employerService';
import HrmsTextInput from '../utilities/customFormControls/HrmsTextInput';

export default function EmployerAdd() {

   

    const [e, setE] = useState([])
    let employerService = new EmployerService()
        function addEmployer(employer){
           
            employerService.addEmployer(employer).then(result =>setE(result.data.data))
        }

    const initialValues = {
        companyName: "", email: "",
        password: "", passwordRepeat: "",
        areaCode: "", phoneNumber: "", website: ""
    }

    const schema = Yup.object({
        companyName: Yup.string().required("Şirket adı zorunlu"),
        email: Yup.string().email().required("E-mail adresi zorunlu"),
        password: Yup.string().required("Şifre zorunlu"),
        passwordRepeat: Yup.string().oneOf([Yup.ref("password"),null],"Şifre ve şifre tekrar uyuşmuyor").required("Şifre tekrar zorunlu"),
        areaCode: Yup.string().required("Alan kodu zorunlu"),
        phoneNumber: Yup.string().required("Telefon numarası zorunlu"),
        website: Yup.string().required("E-posta domainiyle aynı website adresi gerekli")

    })

    return (
        <div>
            <Formik 
                initialValues={initialValues}
                validationSchema={schema}
                onSubmit={(values) => {
                  const employer = {
                        companyName:values.companyName,
                        member:{email:values.email,password:values.password,passwordRepeat:values.passwordRepeat},
                        phoneNumbers:[{areaCode:values.areaCode,phoneNumber:values.phoneNumber}],
                        websites:[{ website:values.website}]  
    }       
                   addEmployer(employer)
                 
                   
                }}
            >
                <Form className="ui form">
                    <HrmsTextInput name="companyName" placeholder="Şirket adı"></HrmsTextInput>
                    <HrmsTextInput name="website" placeholder="Website Adresi"></HrmsTextInput>
                   
                    <HrmsTextInput name="email" placeholder="E-mail adresi"></HrmsTextInput>
                    <HrmsTextInput name="password" placeholder="Şifre" type="password"></HrmsTextInput>
                    <HrmsTextInput name="passwordRepeat" placeholder="Şifre tekrar" type="password"></HrmsTextInput>
                    
                    <HrmsTextInput name="areaCode" placeholder="Alan kodu"></HrmsTextInput>
                    <HrmsTextInput name="phoneNumber" placeholder="Telefon numarası"></HrmsTextInput>
                    
                    
                    <Button color="green" type="submit">Üye ol</Button>
                </Form>


            </Formik>
        </div>
    )
}
