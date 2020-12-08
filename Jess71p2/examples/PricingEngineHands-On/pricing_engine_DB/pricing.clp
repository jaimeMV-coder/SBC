;; First define templates for the model classes so we can use them
;; in our pricing rules. This doesn't create any model objects --
;; it just tells Jess to examine the classes and set up templates
;; using their properties

(import gov.sandia.jess.example.pricing.model.*)
(deftemplate Order       (declare (from-class Order)))
(deftemplate OrderItem   (declare (from-class OrderItem)))
(deftemplate CatalogItem (declare (from-class CatalogItem)))
(deftemplate Customer    (declare (from-class Customer)))
(deftemplate CreditCard  (declare (from-class CreditCard)))

;; Now define the pricing rules themselves. Each rule matches a set
;; of conditions and then creates an Offer object to represent a
;; bonus of some kind given to a customer. The rules assume that
;; there will be just one Order, its OrderItems, and its Customer in 
;; working memory, along with all the CatalogItems.


;;Nuevas reglas

(defrule 12Iphone-months-interest
    "In the purchase of an /iPhone 12 /, with Banamex cards, offer 12 months without interest."
    (CatalogItem (partNumber ?partNumber) (description /iPhone 11 Pro/) (price ?price))
    (CreditCard (name /Banamex/)(OBJECT ?c))
    (OrderItem (partNumber ?partNumber))
    =>
    (add (new Offer "iPhone 12-monthly payment"   (/ ?price 12))))


(defrule 30%-volume-discount
    "Descuento del 30% para /iphone12/."
    (CatalogItem (partNumber ?partNumber) (description /iphone12/) (price ?price))
    (OrderItem (partNumber ?partNumber))
    =>
    (add (new Offer "30% volume discount" (/ ?price 30))))

(defrule 100pesosCupon
    "When buying a /MacBook Air/, offer 100 pesos in cupons for every 1000 pesos in the purchase."
    (CatalogItem (partNumber ?partNumber) (description /MacBook Air/) (price ?price))
    (OrderItem (partNumber ?partNumber))
    =>
    (add (new Offer "Coupons"   (div ?price 1000))))


;;Reglas existentes
(defrule 10%-volume-discount
    "Give a 10% discount to everybody who spends more than $100."
    ?o <- (Order {total > 100})
    =>
    (add (new Offer "10% volume discount" (/ ?o.total 10))))

(defrule 25%-multi-item-discount
    "Give a 25% discount on items the customer buys three or more of."
    (OrderItem {quantity >= 3} (price ?price))
    =>
    (add (new Offer "25% multi-item discount" (/ ?price 4))))

(defrule free-cd-rw-disks
    "If somebody buys a CD writer, send them a free sample of CD-RW
    disks, catalog number 782321; but only if they're a repeat customer.
    We use a regular expression to match the CD writer's description."
    (CatalogItem (partNumber ?partNumber) (description /CD Writer/))
    (CatalogItem (partNumber 782321) (price ?price))
    (OrderItem (partNumber ?partNumber))
    (Customer {orderCount > 1})
    =>	
    (add (new Offer "Free CD-RW disks" ?price)))
