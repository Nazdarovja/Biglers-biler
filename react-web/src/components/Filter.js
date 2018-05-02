import React from 'react';

export default class Filter extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            Mini: false,
            Economy: false,
            Standard: false,
            Premium: false,
            Luxury: false
        }
    }

  handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.checked;
    this.setState({name: value});
    this.props.filter(this.state);
  }


    render() {
        return (
            <form>
                <label>Mini 
                    <input name="mini" type="checkbox" checked={this.state.mini} onChange={this.handleChange} />
                </label>
                <label>Economy 
                    <input name="economy" type="checkbox" checked={this.state.economy} onChange={this.handleChange} />
                </label>
                <label>Standard
                    <input name="standard" type="checkbox" checked={this.state.standard} onChange={this.handleChange} />
                </label>
                <label>Premium
                    <input name="premium" type="checkbox" checked={this.state.premium} onChange={this.handleChange} />
                </label>
                <label>Luxury 
                    <input name="luxury" type="checkbox" checked={this.state.luxury} onChange={this.handleChange} />
                </label>
            </form>

        );
    }
}