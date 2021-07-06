import React, { useState, useEffect } from 'react'
import { Card, Container, Grid } from 'semantic-ui-react'
import EmployerService from '../services/employerService'

export default function Employer() {
  const [employers, setEmployers] = useState([])

  useEffect(() => {
    let employerService = new EmployerService()
    employerService.getEmployers().then(result => setEmployers(result.data.data))
  })

let metatemp=""
  return (
    
    <div>
      <Container className="main">
        <Grid>
          {
            employers.map(employer => (

              <Card key={employer.id} centered raised
               
                href='#card-example-link-card'
                header={employer.companyName}
                
                  {

                    ...employer.phoneNumbers!=null? 
                    employer.phoneNumbers.forEach(number => {
                    metatemp +=  number.phoneNumber + "\n"
                    })
                    
                :
                ''
                }
                meta = {metatemp}
                {...metatemp =""}
                description={employer.member.email}

              />
            ))
            
          }
        </Grid>
      </Container>
    </div>
  )

}
