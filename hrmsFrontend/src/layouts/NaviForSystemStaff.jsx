import React from 'react'
import { Menu,Container } from 'semantic-ui-react'
export default function NaviForSystemStaff() {
    return (
        <div>
        <Menu inverted fixed="top" size="tiny">
            <Container>
        <Menu.Item>
          <img src='/logo192.png' />
        </Menu.Item>

        <Menu.Item
          name='profile'

        >
          Profile
        </Menu.Item>

        <Menu.Item
          name='confirm'

        >
          Confirm
        </Menu.Item>
        </Container>
      </Menu>
        </div>
    )
}
