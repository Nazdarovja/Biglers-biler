import React from 'react';
import {View, Text, CheckBox} from 'react-native';

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

  handleCategoryChange = async (e) => {
    console.log(e);
    const name = e.target.name;
    const value = e.target.checked;
    let categories = Object.assign({}, this.state.categories);
    categories[name] = value;
    await this.setState({categories});

    const filterCategory = this.checkIfFilterShouldHappen(this.state.categories);
    const filterCompany = this.checkIfFilterShouldHappen(this.state.companies);

    this.props.filter(filterCategory, filterCompany);
  }

  handleCompanyChange = async (event) => {
    const name = event.target.name;
    const value = event.target.checked;
    let companies = Object.assign({}, this.state.companies);
    companies[name] = value;
    await this.setState({companies});

    const categoryFilters = this.checkIfFilterShouldHappen(this.state.categories);
    const companyFilters = this.checkIfFilterShouldHappen(this.state.companies);

    this.props.filter(categoryFilters, companyFilters);
  }

  checkIfFilterShouldHappen = (data) => {
      let filters = [];
      for(let item in data) {
          if(data[item] === true) 
            filters.push(item);
      }
      return filters;
  }

    render() {
        return (
            <View>
                    <Text>Categories</Text>
                    <CheckBox style={{width: 20, height: 20}} value={true}/>
                    {/*<input name="Economy" type="checkbox" checked={this.state.categories.Economy} onClick={this.handleCategoryChange} />
                    <input name="Standard" type="checkbox" checked={this.state.categories.Standard} onClick={this.handleCategoryChange} />
                    <input name="Premium" type="checkbox" checked={this.state.categories.Premium} onClick={this.handleCategoryChange} />
                    <input name="Luxury" type="checkbox" checked={this.state.categories.Luxury} onClick={this.handleCategoryChange} />

               Companies
                    <input name="BiglersBigler" type="checkbox" checked={this.state.companies.BiglersBigler} onClick={this.handleCompanyChange} />
                    <input name="Gert" type="checkbox" checked={this.state.companies.Gert} onClick={this.handleCompanyChange} />
                    <input name="Elias" type="checkbox" checked={this.state.companies.Elias} onClick={this.handleCompanyChange} />
        <input name="Devran" type="checkbox" checked={this.state.companies.Devran} onClick={this.handleCompanyChange} />*/}
            
            </View>
        );
    }
}