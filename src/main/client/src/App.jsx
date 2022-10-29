import {useEffect, useState} from 'react'
import './App.css'
import {HashRouter, Link, Route, Router, Routes, useNavigate} from "react-router-dom";
import {createHashHistory} from 'history';

const history = createHashHistory();

function FrontPage() {

    return (
        <div>
            <h1>Best webshop off all time!!!</h1>
            <button><Link to={"/shop"}>Show all items</Link></button>
            <br/>
            <br/>
            <button><Link to={"/addproduct"}>Add product</Link></button>
        </div>
    )
}

function ListAllProduct() {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(async () => {
        const res = await fetch("/api/products");
        setProducts(await res.json());
        setLoading(false);
    }, []);

    if (loading) {
        return (
            <div>Loading...</div>
        )
    }

    function handelClick() {

        history.push('/');
        window.location.reload(false);
    }

    return (
        <div>
            <h1>Here are the shop!</h1>
            {products.map(p =>
                <div class="container">
                    <div class="product-details">
                        <p>name: {p.name}</p>
                        <p>category: {p.category}</p>
                        <p>details: <p>{p.description}</p></p>
                        <p>price: {p.price}</p>
                        <p>stock: {p.stock}</p>
                    </div>
                    <div class="product-image">
                        <img
                            src={`${p.img}`}
                            alt=""/>
                    </div>
                </div>
            )}
            <button onClick={handelClick}>Back</button>
        </div>

    )
}

function AddProduct() {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [category, setCategory] = useState("");
    const [img, setImg] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState(0);
    const [stock, setStock] = useState(0);

    async function handleOnSubmit(event) {
        event.preventDefault();
        await fetch("/api/products", {
            method: "POST",
            body: JSON.stringify({name, category, img, description, price, stock}),
            headers: {
                "Content-Type": "application/json"
            }
        });

        navigate("..");
    }

    return (
        <div>
            <h1>Add product to the store!</h1>

            <form onSubmit={handleOnSubmit}>
                <div>
                    Product name:<input value={name} type="text" onChange={(e) => setName(e.target.value)}/>
                </div>
                <div>
                    product category:<input value={category} type="text" onChange={(e) => setCategory(e.target.value)}/>
                </div>
                <div>
                    img:<input value={img} type="text" onChange={(e) => setImg(e.target.value)}/>
                </div>
                <div>
                    description:<input value={description} type="text"
                                       onChange={(e) => setDescription(e.target.value)}/>
                </div>
                <div>
                    price:<input value={price} type="number" onChange={(e) => setPrice(parseInt(e.target.value))}/>
                </div>
                <div>
                    In stock:<input value={stock} type="number" onChange={(e) => setStock(parseInt(e.target.value))}/>
                </div>

                <button>Add new Product</button>
            </form>

        </div>
    )
}

function App() {

    return (
        <div className="App">
            <Router basename="/" history={history} location={"/"}>...</Router>
            <HashRouter>
                <Routes>
                    <Route path={"/*"} element={<FrontPage/>}/>
                    <Route path={"/addproduct"} element={<AddProduct/>}/>
                    <Route path={"/shop"} element={<ListAllProduct/>}/>
                </Routes>
            </HashRouter>
        </div>
    )

}

export default App
