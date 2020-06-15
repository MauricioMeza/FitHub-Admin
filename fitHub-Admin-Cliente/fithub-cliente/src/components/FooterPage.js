import React from "react";
import {Link} from "react-router-dom";
import { MDBCol, MDBContainer, MDBRow, MDBFooter } from "mdbreact";


const FooterPage = () => {
  return (
    <MDBFooter color="blue-grey" className="page-footer font-small lighten-5 pt-0">
      <br></br>
      <MDBContainer className="mt-5 mb-4 text-center text-md-left">
        <MDBRow className="mt-3">
          <MDBCol md="2" lg="3" xl="3" className="mb-4 dark-grey-text">
            <h6 className="text-uppercase font-weight-bold">
              <strong>Fit Hub</strong>
            </h6>
            <hr className="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style={{ width: "60px" }} />
            <p>
              Empieza tu entrenamiento, obten excelentes resultados con los mejores precios 
              y el gimnasio mas completo.
            </p>
          </MDBCol>
          <MDBCol md="2" lg="2" xl="2" className="mb-4 dark-grey-text">
            <h6 className="text-uppercase font-weight-bold">
              <strong>Servicios</strong>
            </h6>
            <hr className="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style={{ width: "60px" }} />
            <p>
              <a href="#!" className="dark-grey-text">
                Planes 
              </a>
            </p>
            <p>
              <a href="#!" className="dark-grey-text">
                Entrenador personal
              </a>
            </p>
            <p>
              <a href="#!" className="dark-grey-text">
              Clases particulares
              </a>
            </p>
          </MDBCol>
          <MDBCol md="3" lg="2" xl="2" className="mb-4 dark-grey-text">
            <h6 className="text-uppercase font-weight-bold">
              <strong>Links</strong>
            </h6>
            <hr className="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style={{ width: "60px" }} />
            <p>
              <a href="#!" className="dark-grey-text">
                Tu cuenta
              </a>
            </p>
            <p>
              <a href="#!" className="dark-grey-text">
                Afiliate
              </a>
            </p>
            <p>
              <a href="#!" className="dark-grey-text">
                Nuestros planes
              </a>
            </p>
          </MDBCol>
          <MDBCol md="4" lg="3" xl="3" className="mb-4 dark-grey-text">
            <h6 className="text-uppercase font-weight-bold">
              <strong>Contactanos</strong>
            </h6>
            <hr className="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style={{ width: "60px" }} />
            <p>
              <i className="fa fa-home mr-3" /> Bogotá - Colombia
            </p>
            <p>
              <i className="fa fa-envelope mr-3" /> admin@fithub.com
            </p>
            <p>
              <i className="fa fa-phone mr-3" /> + 01 234 567 88
            </p>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
      <div className="footer-copyright text-center py-3">
        <MDBContainer fluid>
          &copy; {new Date().getFullYear()} Copyright:{" "}
          <Link to={"/"}> FITHUB Admin </Link>
        </MDBContainer>
      </div>
    </MDBFooter>
  );
}

export default FooterPage;