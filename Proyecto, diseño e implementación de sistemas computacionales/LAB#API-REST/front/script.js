document.addEventListener("DOMContentLoaded", function () {
    const API_URL = "http://localhost:8080/api/v1/products"; // Reemplaza con la URL de tu API

    const productForm = document.getElementById("productForm");
    const productTableBody = document.getElementById("productTableBody");

    const fetchProducts = async () => {
        const response = await fetch(API_URL);
        const products = await response.json();
        console.log(products);
        renderProducts(products);
    };

    const renderProducts = (products) => {
        productTableBody.innerHTML = "";
        products.forEach(product => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editProduct(${product.id})">Editar</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.id})">Eliminar</button>
                </td>
            `;
            productTableBody.appendChild(row);
        });
    };

    const saveProduct = async (product) => {
        console.log(product);
        const response = await fetch(API_URL, {
            method: product.id ? "PUT" : "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(product),
        });
        fetchProducts();
    };

    window.deleteProduct = async (id) => {
        console.log(id);
        await fetch(`${API_URL}/${id}`, {
            method: "DELETE",
        });
        fetchProducts();
    };

    window.editProduct = async (id) => {
        const response = await fetch(`${API_URL}/${id}`);
        const product = await response.json();
        document.getElementById("productId").value = product.id;
        document.getElementById("name").value = product.name;
        document.getElementById("price").value = product.price;
        document.getElementById("quantity").value = product.quantity;
        $('#productModal').modal('show');
    };

    productForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const product = {
            id: document.getElementById("productId").value,
            name: document.getElementById("name").value,
            price: document.getElementById("price").value,
            quantity: document.getElementById("quantity").value,
        };
        saveProduct(product);
        $('#productModal').modal('hide');
        productForm.reset();
    });

    fetchProducts();
});