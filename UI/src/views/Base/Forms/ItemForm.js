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

class ItemForm extends Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.toggleFade = this.toggleFade.bind(this);

    this.state = {
      selectedCategoryid: "",
      title: "",
      text: "",
      price: "",
      collapse: true,
      selectedItemCategory: "",
      allItems: [],
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

  changeTitle = evs => {
    console.log(evs.target.value);
    this.setState({ title: evs.target.value });
  };

  changeText = event => {
    this.setState({ text: event.target.value });
  };

  changePrice = ev => {
    this.setState({ price: ev.target.value });
  };

  addItem = () => {
    let req = {
      catId: this.state.selectedCategoryid,
      title: this.state.title,
      text: this.state.text,
      price: this.state.price
    };
    console.log("add item Req", req);
    userService
      .addItem(req)
      .then(response => {
        console.log(response);

        if (response.data.success) {
          this.setState({
            visible: true,
            title: "",
            text: "",
            price: "",
            msg: "Saved SuccessFully",
            isSuccess: true
          });
        } else {
          this.setState({
            visible: true,
            title: "",
            text: "",
            price: "",
            msg: response.data.errorMsgEn,
            isSuccess: false
          });
        }

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

  selectCategory = e => {
    console.log(e.target.value);
    this.setState({ selectedCategoryid: e.target.value });
  };

  selectItemCategory = er => {
    console.log(er.target.value);
    this.setState({ selectedItemCategory: er.target.value });
    let id = er.target.value;

    userService
      .getItembyCategory(id)
      .then(response => {
        console.log(response, "All Items");
        this.setState({ allItems: response.data.data });
      })
      .catch(error => {
        this.setState({
          visible: true,
          msg: "Try After Some time !!",
          isSuccess: false
        });
      });
  };

  handleClick = event => {
    this.setState({
      currentPage: Number(event.target.id)
    });
  };

  componentWillMount() {
    axios
      .get("http://localhost:9000/category")
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
      todosPerPage,
      allItems
    } = this.state;
    const indexOfLastTodo = currentPage * todosPerPage;
    const indexOfFirstTodo = indexOfLastTodo - todosPerPage;
    const currentTodos = allItems.slice(indexOfFirstTodo, indexOfLastTodo);
    const pageNumbers = [];
    for (let i = 1; i <= Math.ceil(allItems.length / todosPerPage); i++) {
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

    const renderTableData = cate =>
      cate.map((todo, index) => {
        return (
          <tr key={index}>
            <td>{index + 1}</td>
            <td>{todo.title}</td>
            <td>{todo.text}</td>
            <td>{todo.price}</td>
          </tr>
        );
      });

    const renderData = cate =>
      cate.map((todo, index) => {
        return <option value={todo.id}>{todo.title}</option>;
      });

    return (
      <div className="animated fadeIn">
        <Row>
          <Col xs="12" sm="5">
            <Card>
              <CardHeader>Add Items</CardHeader>
              <CardBody>
                <Alert
                  isOpen={visible}
                  color={isSuccess ? "success" : "danger"}
                >
                  {msg}
                </Alert>

                <FormGroup row>
                  <Col xs="12">
                    <Input
                      type="select"
                      name="Select Category"
                      id="select"
                      onChange={this.selectCategory}
                    >
                      <option value="0">Select Category</option>
                      {renderData(category)}
                    </Input>
                  </Col>
                </FormGroup>

                <FormGroup row>
                  <Col md="12">
                    <InputGroup>
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="fa fa-tag" />
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        type="text"
                        id="input1-group1"
                        name="input1-group1"
                        placeholder="Item Name"
                        value={this.state.title}
                        onChange={this.changeTitle}
                      />
                    </InputGroup>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Col md="12">
                    <InputGroup>
                      <Input
                        type="text"
                        id="input2-group1"
                        name="input2-group1"
                        value={this.state.text}
                        placeholder="Short Description"
                        onChange={this.changeText}
                      />
                      <InputGroupAddon addonType="append">
                        <InputGroupText>
                          <i className="fa fa-ellipsis-h" />
                        </InputGroupText>
                      </InputGroupAddon>
                    </InputGroup>
                  </Col>
                </FormGroup>
                <FormGroup row>
                  <Col md="12">
                    <InputGroup>
                      <InputGroupAddon addonType="prepend">
                        <InputGroupText>
                          <i className="fa fa-euro" />
                        </InputGroupText>
                      </InputGroupAddon>
                      <Input
                        type="number"
                        id="input3-group1"
                        name="input3-group1"
                        value={this.state.price}
                        placeholder="Item price"
                        onChange={this.changePrice}
                      />
                      <InputGroupAddon addonType="append">
                        <InputGroupText>.00</InputGroupText>
                      </InputGroupAddon>
                    </InputGroup>
                  </Col>
                </FormGroup>
                <FormGroup className="form-actions">
                  <Button
                    type="button"
                    onClick={this.addItem}
                    size="sm"
                    color="success"
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
                <i className="fa fa-align-justify" /> View Items Category Wise
              </CardHeader>
              <CardBody>
                <FormGroup row>
                  <Col xs="12">
                    <Input
                      type="select"
                      name="Select Category"
                      id="select"
                      onChange={this.selectItemCategory}
                    >
                      <option value="0">Select Category</option>
                      {renderData(category)}
                    </Input>
                  </Col>
                </FormGroup>

                <Table responsive>
                  <thead>
                    <tr>
                      <th>SL</th>
                      <th>Title</th>
                      <th>Text</th>
                      <th>Price</th>
                    </tr>
                  </thead>
                  <tbody>{renderTableData(currentTodos)}</tbody>
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

export default ItemForm;
