import { ErrorMessage, Field, useField } from 'formik'
import React from 'react'
import {  FormField, Label } from 'semantic-ui-react'

export default function HrmsInput({ ...props }) {
   

    return (
        <div>
           
            <FormField>
                <Field name={props.name} placeholder={props.placeholder} defaultValue={props.defaultValue} ></Field>
                <ErrorMessage name={props.name} render={error =>
                    <Label pointing basic color="red" content={error}></Label>
                }></ErrorMessage>
            </FormField>
        </div>
    )
}
