export const usersTypeDef = `
type User {
    id: Int!
    firstName: String!
    lastName: String!
    username: String!
    password: String!
}
input UserInput {
    firstName: String!
    lastName: String!
    username: String!
    password: String!
}`;

export const usersQueries = `
    allUsers: [User]!
    userById(id: Int!): User!
`;

export const usersMutations = `
    createUser(user: UserInput!): User!
    updateUser(id: Int!, user: UserInput!): User!
    deleteUser(id: Int!): Int
`;
