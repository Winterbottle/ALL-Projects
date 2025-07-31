print("Solution for Assingnment3 Task 2");
print("============================");
print("Student number: 10241485"    );
print("Name: Ho Ka Yan Jeslyn"         );
print("Date: 15 May 2024"           );
print("============================");
print("");
print("");
print("Q1. List the customer’s name, address and the created shopping cart who purchased product P1003.");
db.shoppingCart.aggregate([{$unwind: 
	"$CUSTOMER.creates.SHOPPINGCART" },
    { $unwind: 
	"$CUSTOMER.creates.SHOPPINGCART.containsProdList" },
    { $match: {
	"CUSTOMER.creates.SHOPPINGCART.containsProdList.productId": "P1003" } },
    {$project: {
	"customerName": "$CUSTOMER.customerName",
        "customerAddress": "$CUSTOMER.customerAddress",
        "createdShoppingCart": "$CUSTOMER.creates.SHOPPINGCART"}}
]).pretty()
print("");
print("========================================================================================================================================================================================================");
print("Q2. List the customer’s name, address, and the created shopping cart of the customer who created a shopping cart on 11 May 2024 (ISODate('2024-05-11T00:00:00Z')). Please do not show the customer's id.");
db.shoppingCart.aggregate([{ $unwind: "$CUSTOMER.creates.SHOPPINGCART" },
			    { $match: { 
				"CUSTOMER.creates.SHOPPINGCART.dateCreated": {
				    $gte: ISODate("2024-05-11T00:00:00Z"),
				    $lt: ISODate("2024-05-12T00:00:00Z")
				} } },
			    { $project: {
				    "customerName": "$CUSTOMER.customerName",
				    "customerAddress": "$CUSTOMER.customerAddress",
				    "createdShoppingCart": "$CUSTOMER.creates.SHOPPINGCART"
				}},
			    { $project: {
				    "_id": 0, 
				    "customerName": 1,
				    "customerAddress": 1,
				    "createdShoppingCart": 1
				}}
]).pretty()
print("");
print("========================================================================================================================================================================================================");
print("Q3. Find the total number of shopping carts created by each customer. For each customer, list his/her email address and the total number of shopping cart created.");
db.shoppingCart.aggregate([{ $unwind: "$CUSTOMER.creates.SHOPPINGCART" },
			    { $group: {
				_id: "$CUSTOMER.customerEmail", 
				totalCarts: { $sum: 1 } 
			       } },
			    { $project: {
				"customerEmail": "$_id",
				"totalCarts": "$totalCarts",
				"createdShoppingCart": "$CUSTOMER.creates.SHOPPINGCART",
				_id: 0 
			    }}
]).pretty()
print("");
print("========================================================================================================================================================================================================");
print("Q4. Find the products that have been included in at least 2 or 3 shopping carts.");
db.shoppingCart.aggregate([{ $unwind: "$PRODUCT.includedIn" },
			    { $group: {
				_id: "$PRODUCT.productId", 
				count: { $sum: 1 } 
			       } },

			    { $match: { 
				count: { $gte: 2 } 
				} }   
]).pretty()
print("");
print("========================================================================================================================================================================================================");
print("Q5. For each price base, list the price base and the total number of each price base.");
db.shoppingCart.aggregate([{ $group: {
				_id: "$PRODUCT.price.base",
				total: { $sum: 1 }
			    } }
]).pretty()
print("");
print("========================================================================================================================================================================================================");
print("Q6. Find the customers who have purchased both the products 'P1002' and 'P1003'.");
db.shoppingCart.aggregate([{$match:{"CUSTOMER.creates.SHOPPINGCART.containsProdList.productId":{ $all: ["P1002", "P1003"] }
			    }},
			    { $group: {
				_id: "$CUSTOMER.customerId"
			    } }  
]).pretty()
print("");
print("========================================================================================================================================================================================================");
print("Q7. Find the products that have not been included in any of the shopping carts.");
db.shoppingCart.aggregate([{ $project: {
				"PRODUCT.productId": 1
			      }},
			    { $lookup: {
				from: "shoppingCart",
				localField: "PRODUCT.productId",
				foreignField: "CUSTOMER.creates.SHOPPINGCART.containsProdList.productId",
				as: "includedInCarts"
			      }},
			    { $match: {
				includedInCarts: { $eq: [] }
			      } },
			    { $project: {
				"_id": 0,
				"productId": "$PRODUCT.productId"
			      }}
  ]).pretty()
  print("");
print("========================================================================================================================================================================================================");
print("Q8. Find the total number of customers who do not provide his/her telephone number.");
db.shoppingCart.find({ 
    "CUSTOMER": { "$exists": true },
    "CUSTOMER.customerPhone": { $exists: false } 
}).count()
print("");
print("========================================================================================================================================================================================================");
print("Q9. Update the closing date (dateClosed) of the cart ‘cart001’ of the customer ‘C92378’ to 15 May 2024. (Hint. You can use the function new Date(“2024-05-15” to set the date.)");
print("==========================");
print("before updating the closing date")
db.shoppingCart.find({"CUSTOMER.customerId":"C92378",
"CUSTOMER.creates.SHOPPINGCART.cartId": "cart001"
}).pretty()
print("");
print("===========================")
print("Updating the closing date")
db.shoppingCart.update({"CUSTOMER.customerId":"C92378",
    			"CUSTOMER.creates.SHOPPINGCART.cartId": "cart001"}, 
    			{ $set: { "CUSTOMER.creates.SHOPPINGCART.$.dateClosed": new Date("2024-05-15") } }
)
print("");
print("===========================")
print("After updating the closing date")
db.shoppingCart.find({"CUSTOMER.customerId":"C92378",
"CUSTOMER.creates.SHOPPINGCART.cartId": "cart001"
}).pretty()
print("");
print("");
print("========================================================================================================================================================================================================");
print("10. Delete from the collection a shoppingcart (cart005) created by the customer C78263.");
print("==========================");
print("before Deleting from the collection a shoppingcart")
db.shoppingCart.find({"CUSTOMER.customerId": "C78263"}).pretty()
print("==========================");
print(" Deleting from the collection a shoppingcart")
db.shoppingCart.updateOne({ "CUSTOMER.customerId": "C78263" },
    { $pull: { "CUSTOMER.creates.SHOPPINGCART": { cartId: "cart005" } } }
)
print("==========================");
print("After Deleting from the collection a shoppingcart")
db.shoppingCart.find({"CUSTOMER.customerId": "C78263"}).pretty()
print("");