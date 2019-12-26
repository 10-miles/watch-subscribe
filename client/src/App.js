import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Container, Button } from 'reactstrap';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Container className="themed-container">
          <p>Watch Your Subscribe</p>
          <Button color="primary" size="lg" block>Google Login button</Button>
        </Container>
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
      </header>
    </div>
  );
}

export default App;
