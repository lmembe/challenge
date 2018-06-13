1. I setup lombok.
2. I had compilation errors which I corrected
    - Accounts class was missing:
               - getBalance() method
3. Created IMoneyTransferService Class which is an Interface
4. Created MoneyTransferService Class which implements IMoneyTransferService Interface
5. Created MoneyTransferController Class which calls method in the MoneyTransferService
6. Created JUnit MoneyTransferControllerTest for testing MoneyTransferController
7. Created JUnit MoneyTransferServiceTest for testing MoneyTransferService
8. Created gradle.properties so that Gradle could find my Java 8 SDK installation