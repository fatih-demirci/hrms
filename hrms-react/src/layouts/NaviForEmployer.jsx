import React from 'react'
import { Menu, Dropdown, Grid } from 'semantic-ui-react'
export default function NaviForEmployer({ signOut }) {
  return (
    <div>


      <Grid className="employerCenter">

        <Menu.Item>
          <Dropdown pointing="top left" text="Şirket Adı">
            <Dropdown.Menu>
              <Dropdown.Item text="Profil" icon="info" />
              <Dropdown.Item onClick={signOut} text="Çıkış Yap" icon="sign-out" />

            </Dropdown.Menu>
          </Dropdown>
        </Menu.Item>

        <Menu.Item
          name='profile'

        >
          Profile
        </Menu.Item>

        <Menu.Item
          name='Add Advertisement'

        >
          Add Advertisement
        </Menu.Item>

        <Menu.Item
          name='view cv'

        >
          View Cv's
        </Menu.Item>
      </Grid>


    </div>
  )
}
