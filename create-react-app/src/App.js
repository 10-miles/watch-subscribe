import React, {Component} from 'react';
import './App.css';

class MyComponent extends Component
{
  handleOnClick = () => {
    console.log("Click")
  };

  render(){
    return(
      <div className={"App"}>
        <header className={"App-header"}>
          <button onClick = {this.handleOnClick}>
            Gmail login
          </button>
        </header>
      </div>
    )
  }
}

export default MyComponent;