import React from 'react';

export default class Filter extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: {
                Mini: false,
                Economy: false,
                Standard: false,
                Premium: false,
                Luxury: false
            },
            companies: {
                BiglersBigler: false,
                Gert: false,
                Elias: false,
                Devran: false
            }
        }
    }

  handleCategoryChange = async (event) => {
    event.preventDefault();
    const name = event.target.name;
    const value = event.target.checked;
    let categories = Object.assign({}, this.state.categories);
    categories[name] = value;
    await this.setState({categories});

    const filterCategory = this.checkIfFilterShouldHappen(this.state.categories);
    const filterCompany = this.checkIfFilterShouldHappen(this.state.companies);

    this.props.filter(this.state, filterCategory, filterCompany);
  }

  handleCompanyChange = async (event) => {
    event.preventDefault();
    const name = event.target.name;
    const value = event.target.checked;
    let companies = Object.assign({}, this.state.companies);
    companies[name] = value;
    await this.setState({companies});

    const filterCategory = this.checkIfFilterShouldHappen(this.state.categories);
    const filterCompany = this.checkIfFilterShouldHappen(this.state.companies);

    this.props.filter(this.state, filterCategory, filterCompany);
  }

  checkIfFilterShouldHappen = (data) => {
      for(let item in data) {
          if(item)
            return true;
      }
      return false;
  }


    render() {
        return (
            <div>
            <form>
                Categories
                <label>Mini 
                    <input name="Mini" type="checkbox" checked={this.state.categories.Mini} onClick={this.handleCategoryChange} />
                </label>
                <label>Economy 
                    <input name="Economy" type="checkbox" checked={this.state.categories.Economy} onClick={this.handleCategoryChange} />
                </label>
                <label>Standard
                    <input name="Standard" type="checkbox" checked={this.state.categories.Standard} onClick={this.handleCategoryChange} />
                </label>
                <label>Premium
                    <input name="Premium" type="checkbox" checked={this.state.categories.Premium} onClick={this.handleCategoryChange} />
                </label>
                <label>Luxury 
                    <input name="Luxury" type="checkbox" checked={this.state.categories.Luxury} onClick={this.handleCategoryChange} />
                </label>

               Companies
               <label>Biglers Biler
                    <input name="BiglersBigler" type="checkbox" checked={this.state.companies.BiglersBigler} onClick={this.handleCompanyChange} />
                </label>
                <label>Gert 
                    <input name="Gert" type="checkbox" checked={this.state.companies.Gert} onClick={this.handleCompanyChange} />
                </label>
                <label>Elias
                    <input name="Elias" type="checkbox" checked={this.state.companies.Elias} onClick={this.handleCompanyChange} />
                </label>
                <label>Devran
                    <input name="Devran" type="checkbox" checked={this.state.companies.Devran} onClick={this.handleCompanyChange} />
                </label>
            </form>
            <p style={{fontSize: 10}}>{JSON.stringify(this.state)}</p>
            </div>

        );
    }
}