import React, { useEffect, useState } from 'react'

import EmployerService from '../services/employerService'
import * as Yup from 'yup';
import { Form, Formik } from 'formik';
import { Button, Container, Grid, GridRow, Label } from 'semantic-ui-react';
import HrmsTextInput from '../utilities/customFormControls/HrmsTextInput';
import CompanyNameUpdateService from '../services/companyNameUpdateService';
import EmailUpdateService from '../services/EmailUpdateService';
import WebsiteService from '../services/websiteService';
import PhoneNumberService from '../services/phoneNumberService';

export default function EmployerEdit() {

    let companyNameUpdateService = new CompanyNameUpdateService()
    let emailUpdateService = new EmailUpdateService()
    let employerService = new EmployerService()
    let websiteService = new WebsiteService()
    let phoneNumberService = new PhoneNumberService()

    const [employer, setEmployer] = useState([])
    const [websiteUpdate, setWebsiteUpdate] = useState([])
    const [phoneNumberUpdate, setPhoneNumberUpdate] = useState([])

    useEffect(() => {
        employerService.getAllEmployerJobAdvertisements(149).then(result => setEmployer(result.data.data))
    }, [])

    function IsWebsiteUpdate(id) {

        useEffect(() => {
            websiteService.websiteIsUpdate(id).then(result => setWebsiteUpdate(result.data.data))
        }, [])

    }

    function IsPhoneNumberUpdate(employerId, exPhoneNumberId) {

        useEffect(() => {
            phoneNumberService.isUpdate(employerId, exPhoneNumberId).then(result => setPhoneNumberUpdate(result.data.data))
        }, [])

    }

    return (
        <div>
            <h4>Şirket adını değiştir</h4>

            {employer.member != undefined ?
                <Formik
                    validationSchema={Yup.object({
                        companyName: Yup.string("Geçersiz iş yeri adı").required("İş yeri adı gerekli"),
                    })}
                    initialValues={{
                        companyName: employer.companyName
                    }}
                    onSubmit={(values) => {
                        companyNameUpdateService.companyNameUpdate(149, values.companyName)
                    }}
                >
                    {formik => (
                        <Form className="ui form">

                            <HrmsTextInput name="companyName" placeholder="İş yeri adı" onChange={formik.handleChange} ></HrmsTextInput>

                            <Grid>

                                <GridRow centered>
                                    {employer.companyNameUpdate !== null ?
                                        <Label>{employer.companyNameUpdate.companyNameUpdate} olarak güncellenmesi için personel onayı bekleniyor</Label>
                                        : null
                                    }
                                </GridRow>
                                <GridRow centered>
                                    <Button color="green" type="submit" >Güncelle</Button>
                                </GridRow>
                            </Grid>


                        </Form>
                    )}

                </Formik>
                : null
            }


            <Container className="top" />
            <h4>E-mail adresini değiştir</h4>

            {employer.member != undefined ?
                <Formik
                    validationSchema={Yup.object({
                        email: Yup.string("Geçersiz e-mail adresi").email("Geçersiz e-mail adresi").required("E-mail adresi gerekli")
                    })}
                    initialValues={{
                        email: employer.member.email
                    }}
                    onSubmit={(values) => {
                        emailUpdateService.EmailUpdate(149, values.email)
                        console.log(values)
                    }}
                >
                    {formik => (
                        <Form className="ui form">
                            <HrmsTextInput name="email" placeholder="E-mail adresi" onChange={formik.handleChange}></HrmsTextInput>
                            <Grid>
                                <GridRow centered>
                                    {employer.emailUpdate != null ?
                                        <Label>{employer.emailUpdate.emailUpdate} olarak güncellenmesi için personel onayı bekleniyor</Label>
                                        : null
                                    }
                                </GridRow >
                                <GridRow centered>
                                    <Button color="green" type="submit" >Güncelle</Button>
                                </GridRow>
                            </Grid>

                        </Form>
                    )}

                </Formik>
                : null
            }

            <Container className="top" />
            <h4>Şifre Değiştir</h4>

            {employer.member != undefined ?
                <Formik
                    validationSchema={Yup.object({
                        password: Yup.string("Geçersiz şifre").required("Şifre gerekli"),
                        passwordRepeat: Yup.string("Geçersiz şifre tekrar").oneOf([Yup.ref("password"),null],"Şifre ve şifre tekrar uyuşmuyor").required("Şifre tekrar gerekli")
                    })}
                    initialValues={{
                        password: employer.member.password, passwordRepeat: employer.member.passwordRepeat
                    }}
                    onSubmit={(values) => {
                        employerService.updatePassword(149, values.password, values.passwordRepeat)
                    }}
                >
                    {formik => (

                        <Form className="ui form">
                            <HrmsTextInput name="password" placeholder="Şifre" type="password" onChange={formik.handleChange} ></HrmsTextInput>
                            <HrmsTextInput name="passwordRepeat" placeholder="Şifre tekrar" type="password" onChange={formik.handleChange} ></HrmsTextInput>
                            <Button color="green" type="submit" >Kaydet</Button>
                        </Form>
                    )}

                </Formik>
                : null
            }
            <Container className="top" />

            <h4>Web Sitelerini Güncelle</h4>

            {employer.websites != undefined ?
                employer.websites.map(website => (
                    website.exWebsiteId === 0 ?
                        <Formik key={website.id}
                            validationSchema={Yup.object({
                                website: Yup.string("").required("Website adresi gerekli"),

                            })}
                            initialValues={{
                                website: website.website
                            }}
                            onSubmit={(values) => {
                                const web = {
                                    exWebsiteId: website.id,
                                    website: values.website
                                }
                                websiteService.updateWebsite(149, web)
                            }}
                        >
                            {formik => (

                                <Form className="ui form">
                                    <HrmsTextInput name="website" placeholder="website adresi" onChange={formik.handleChange} ></HrmsTextInput>
                                    <Grid>
                                        <GridRow centered>
                                            {IsWebsiteUpdate(website.id)}
                                            {websiteUpdate !== null ?
                                                <Label>{websiteUpdate.website} olarak güncellenmesi için personel onayı bekleniyor</Label>
                                                : null
                                            }
                                        </GridRow>
                                        <GridRow centered>
                                            <Button color="green" type="submit" >Kaydet</Button>
                                        </GridRow>
                                    </Grid>


                                </Form>
                            )}

                        </Formik>
                        : null

                ))

                : null
            }
            <Container className="top" />

            <h4>Telefon Numaralarını Güncelle</h4>

            {employer.phoneNumbers != undefined ?
                employer.phoneNumbers.map(phoneNumber => (
                    phoneNumber.exPhoneNumberId === 0 ?
                        <Formik key={phoneNumber.id}
                            validationSchema={Yup.object({
                                phoneNumber: Yup.string("").required("Telefon numarası gerekli"),
                                areaCode: Yup.string("").required("Alan kodu gerekli")

                            })}
                            initialValues={{
                                phoneNumber: phoneNumber.phoneNumber,
                                areaCode: phoneNumber.areaCode
                            }}
                            onSubmit={(values) => {
                                const phone={
                                    phoneNumber:values.phoneNumber,
                                    areaCode:values.areaCode,
                                    exPhoneNumberId:phoneNumber.id
                                }
                                phoneNumberService.updatePhoneNumber(149,phone);
                            }}
                        >
                            {formik => (

                                <Form className="ui form">
                                    <Grid>
                                        <Grid.Row>
                                            <Grid.Column width={3}>
                                                <HrmsTextInput name="areaCode" placeholder="Alan kodu" onChange={formik.handleChange} ></HrmsTextInput>
                                            </Grid.Column>
                                            <Grid.Column width={13}>
                                                <HrmsTextInput name="phoneNumber" placeholder="Telefon numarası" onChange={formik.handleChange} ></HrmsTextInput>
                                            </Grid.Column>

                                        </Grid.Row>

                                    </Grid>
                                    <Grid>
                                        <GridRow centered>
                                            {IsPhoneNumberUpdate(149, phoneNumber.id)}
                                            {phoneNumberUpdate !== null ?
                                                <Label>
                                                    {phoneNumberUpdate.areaCode} {phoneNumberUpdate.phoneNumber} olarak güncellenmesi için personel onayı bekleniyor
                                                </Label>
                                                : null
                                            }

                                        </GridRow>
                                    </Grid>
                                    <div style={{ marginTop: 13 }}>
                                        <Button color="green" type="submit" >Kaydet</Button>
                                    </div>


                                </Form>
                            )}

                        </Formik>
                        : null
                ))

                : null
            }

        </div>
    )





}
