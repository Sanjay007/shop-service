import React, { Component } from "react";
import { userService } from "../../../services/userService.js";
import axios from "axios";
import {
  Alert,
  Button,
  Card,
  CardBody,
  CardHeader,
  Col,
  FormGroup,
  Input,
  InputGroup,
  InputGroupAddon,
  InputGroupText,
  Pagination,
  PaginationItem,
  PaginationLink,
  Row,
  Table
} from "reactstrap";

class CategoryForm extends Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.toggleFade = this.toggleFade.bind(this);

    this.state = {
      collapse: true,
      title: "",
      isSuccess: false,
      category: [],
      visible: false,
      fadeIn: true,
      msg: "",
      timeout: 300,
      currentPage: 1,
      todosPerPage: 3
    };
  }

  toggle() {
    this.setState({ collapse: !this.state.collapse });
  }
  categoryChange = e => {
    console.log(e.target.value);
    this.setState({ title: e.target.value });
  };

  addCategory = () => {
    var self = this;
    var req = { title: this.state.title };

    userService
      .addCategory(req)
      .then(response => {
        console.log(response);

        if (response.data.success) {
          this.setState({
            title: "",
            visible: true,
            msg: "Saved SuccessFully",
            isSuccess: true
          });
        } else {
          this.setState({
            visible: true,
            msg: response.data.errorMsgEn,
            isSuccess: false
          });
        }
        userService
          .getAllCategory()
          .then(response => {
            this.setState({ category: response.data.data });
          })
          .catch(error => {
            this.setState({
              visible: true,
              msg: "Try After Some time !!",
              isSuccess: false
            });
          });
      })
      .catch(error => {
        this.setState({
          visible: true,
          msg: "Try After Some time !!",
          isSuccess: false
        });
      });
  };
  toggleFade() {
    this.setState(prevState => {
      return { fadeIn: !prevState };
    });
  }
  handleClick = event => {
    this.setState({
      currentPage: Number(event.target.id)
    });
  };
  componentWillMount() {
    userService
      .getAllCategory()
      .then(response => {
        console.log(response, "All");
        this.setState({ category: response.data.data });
      })
      .catch(error => {
        this.setState({
          visible: true,
          msg: "Try After Some time !!",
          isSuccess: false
        });
      });

    console.log("Mounting");
  }
  render() {
    const {
      msg,
      visible,
      isSuccess,
      category,
      currentPage,
      todosPerPage
    } = this.state;
    const indexOfLastTodo = currentPage * todosPerPage;
    const indexOfFirstTodo = indexOfLastTodo - todosPerPage;
    const currentTodos = category.slice(indexOfFirstTodo, indexOfLastTodo);
    const pageNumbers = [];
    for (let i = 1; i <= Math.ceil(category.length / todosPerPage); i++) {
      pageNumbers.push(i);
    }
    const renderPageNumbers = pageNumbers.map(number => {
      return (
        <PaginationItem active={currentPage === number}>
          <PaginationLink
            key={number}
            id={number}
            onClick={this.handleClick}
            tag="button"
          >
            {number}
          </PaginationLink>
        </PaginationItem>
      );
    });

    const renderData = cate =>
      cate.map((todo, index) => {
        return (
          <tr key={index}>
            <td>{todo.id}</td>
            <td>{todo.title}</td>
          </tr>
        );
      });

    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" sm="5">
            <Card>
              <CardHeader>Add Category</CardHeader>
              <CardBody>
                <Alert
                  isOpen={visible}
                  color={isSuccess ? "success" : "danger"}
                >
                  {msg}
                </Alert>

                <FormGroup>
                  <InputGroup>
                    <InputGroupAddon addonType="prepend">
                      <InputGroupText>CategoryName</InputGroupText>
                    </InputGroupAddon>
                    <Input
                      type="text"
                      value={this.state.title}
                      placeholder="Enter Category Name"
                      onChange={this.categoryChange}
                    />
                  </InputGroup>
                </FormGroup>

                <FormGroup className="form-actions">
                  <Button
                    type="button"
                    size="sm"
                    color="primary"
                    onClick={this.addCategory}
                  >
                    Submit
                  </Button>
                </FormGroup>
              </CardBody>
            </Card>
          </Col>
          <Col xs="12" sm="7">
            <Card>
              <CardHeader>
                <i className="fa fa-align-justify" /> Categories List
              </CardHeader>
              <CardBody>
                <Table responsive>
                  <thead>
                    <tr>
                      <th>SL</th>
                      <th>Category</th>
                    </tr>
                  </thead>
                  <tbody>{renderData(currentTodos)}</tbody>
                </Table>
                <Pagination>
                  <PaginationItem>
                    <PaginationLink
                      key={1}
                      id={1}
                      onClick={this.handleClick}
                      previous
                      tag="button"
                    />
                  </PaginationItem>

                  {renderPageNumbers}
                  <PaginationItem>
                    <PaginationLink
                      key={pageNumbers.length}
                      id={pageNumbers.length}
                      onClick={this.handleClick}
                      next
                      tag="button"
                    />
                  </PaginationItem>
                </Pagination>
              </CardBody>
            </Card>
          </Col>
          <Col xs="12" sm="4" />
        </Row>
      </div>
    );
  }
}

export default CategoryForm;
