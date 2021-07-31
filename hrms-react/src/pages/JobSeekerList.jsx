import React, { useState, useEffect } from 'react'
import { Card, Container, Grid } from 'semantic-ui-react'
import JobSeekerService from '../services/jobSeekerService'
export default function JobSeeker() {
  const [jobSeekers, setJobSeekers] = useState([])
  useEffect(() => {
    let jobSeekerService = new JobSeekerService()
    jobSeekerService.getJobseekers().then(result => setJobSeekers(result.data.data))
  },[])


  return (
    <div>
      <Container className="main">
        <Grid>
          {
            jobSeekers.map(jobSeeker => (

              <Card key={jobSeeker.id} centered raised       
                href="#a"
                header={jobSeeker.name + " " +jobSeeker.lastName}
                meta= {jobSeeker.birthDay}
                description={jobSeeker.member.email}
                image={jobSeeker.cv!=null && jobSeeker.cv.image!=null ?
                    jobSeeker.cv.image.imageURL
                    :
                    'logo192.png'      
                }
                extra={jobSeeker.cv!=null ? jobSeeker.cv.description  
                :
                ''
                
              }

              />
            ))
          }
        </Grid>
      </Container>
    </div>
  )

}
