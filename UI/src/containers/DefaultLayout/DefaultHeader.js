import React, { Component } from "react";
import { DropdownToggle, Nav } from "reactstrap";
import PropTypes from "prop-types";

import {
  AppHeaderDropdown,
  AppNavbarBrand,
  AppSidebarToggler
} from "@coreui/react";

const propTypes = {
  children: PropTypes.node
};

const defaultProps = {};

class DefaultHeader extends Component {
  render() {
    const { children, ...attributes } = this.props;

    return (
      <React.Fragment>
        <AppSidebarToggler className="d-lg-none" display="md" mobile />
        <AppNavbarBrand
          full={{
            src: "https://avatars1.githubusercontent.com/u/5675688?s=460&v=4",
            width: 89,
            height: 25,
            alt: "Idealo Logo"
          }}
          minimized={{
            src: "https://avatars1.githubusercontent.com/u/5675688?s=460&v=4",
            width: 30,
            height: 30,
            alt: "Idealo Logo"
          }}
        />
        <AppSidebarToggler className="d-md-down-none" display="lg" />

        <Nav className="ml-auto" navbar>
          <AppHeaderDropdown direction="down">
            <DropdownToggle nav>
              <img
                src={"../../assets/img/avatars/6.jpg"}
                className="img-avatar"
                alt=""
              />
            </DropdownToggle>
          </AppHeaderDropdown>
        </Nav>
      </React.Fragment>
    );
  }
}

DefaultHeader.propTypes = propTypes;
DefaultHeader.defaultProps = defaultProps;

export default DefaultHeader;
