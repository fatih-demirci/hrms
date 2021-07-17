import React from 'react'
import { NavLink } from 'react-router-dom'
import { Menu,Dropdown,Grid } from 'semantic-ui-react'

export default function NaviForGuest() {
    return (
        <div>
<Grid className="guestCenter">

        <Menu.Item>
          <Dropdown pointing="top left" text="Giriş Yap">
            <Dropdown.Menu>
              <Dropdown.Item text="İş Arayan" icon="user" />
              <Dropdown.Item text="İş Veren" icon="group" />

            </Dropdown.Menu>
          </Dropdown>
        </Menu.Item>

        <Menu.Item>
          <Dropdown pointing="top left" text="Üye Ol">
            <Dropdown.Menu>
              <Dropdown.Item as={NavLink} to="/JobSeeker/Add" text="İş Arayan" icon="user" />
              <Dropdown.Item as={NavLink} to="/Employer/Add" text="İş Veren" icon="group" />

            </Dropdown.Menu>
          </Dropdown>
        </Menu.Item>

        </Grid>
        </div>
    )
}
